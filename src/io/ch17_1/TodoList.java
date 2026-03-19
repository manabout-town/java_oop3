package io.ch17_1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TodoList {

  static final File TODO_DIR = new File("todo.txt");

  static final int ADD_TASK = 1;
  static final int PRINT_TASK = 2;
  static final int COMPLETE_TASK = 3;
  static final int PENDING_TASK = 4;
  static final int CANCEL_TASK = 5;
  static final int EXIT = 0;

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    label:
    while(true){
      System.out.println("=== To-Do 리스트 ===");
      System.out.println("1. 할 일 추가");
      System.out.println("2. 전체 목록 보기");
      System.out.println("3. 완료 처리");
      System.out.println("4. 미완료 목록만 보기");
      System.out.println("5. 완료 취소");
      System.out.println("0. 프로그램 종료");
      System.out.print("선택 : ");
      int choice = sc.nextInt();

      switch (choice) {
        case ADD_TASK:
          addTask(sc);
          break;
        case PRINT_TASK:
          printTask();
          break;
        case COMPLETE_TASK:
          printTask();
          completeTask(sc);
          break;
        case PENDING_TASK:
          pendingTask();
          break;
        case CANCEL_TASK:
          printTask();
          cancelTask(sc);
          break;
        case EXIT:
          System.out.println("프로그램을 종료합니다.");
          break label;
        default:
          System.out.println("알 수 없는 명령. 다시 입력하세요.");
          break;
      }
    }

    sc.close();
  } // end of main


  private static void addTask(Scanner sc) {
    // "[ ] 할 일 내용" 형식으로 지정
    // [ ] -> 미완료 상태
    // [V] -> 완료 상태
    System.out.print("추가할 할 일을 입력하세요 :");
    String task = sc.nextLine();

    try(BufferedWriter bw = new BufferedWriter(new FileWriter(TODO_DIR, true))){
      bw.write("[ ] " + task);
      bw.newLine();
      bw.flush();
      System.out.println("추가되었습니다 : " + task);
    } catch(Exception e){
      throw new RuntimeException(e);
    }
  }

  private static void printTask() {
    System.out.println("\n===전체 할 일 목록===");
    int count = 0;
    try (BufferedReader br = new BufferedReader(new FileReader(TODO_DIR))) {
      String line;
      while ((line = br.readLine()) != null ) {
        System.out.println(++count + ". " + line);
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static void pendingTask() {
    System.out.println("=== 미완료 목록 ===");
    boolean flag = false;

    try (BufferedReader br = new BufferedReader(new FileReader(TODO_DIR))) {
      String checkBox;
      int count = 0;
      while ((checkBox = br.readLine()) != null) {
        if (checkBox.contains("[ ]")) {
          System.out.println(checkBox);
          count++;
          flag = true;
        }
      }
      if(!flag){
        System.out.println("미완료 목록이 존재하지 않습니다.");
      }
      else{
        System.out.println("\n남은 할 일 : " + count + "개");
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  private static void completeTask(Scanner sc) {
    System.out.println("완료할 번호를 입력하세요 : ");
    int taskNum = sc.nextInt();
    sc.nextLine();

    ArrayList<String> taskList = new ArrayList<>();
    boolean flag = false;

    try(BufferedReader br = new BufferedReader(new FileReader(TODO_DIR))){
      String line;
      int count = 1;
      while((line = br.readLine()) != null){
        if(count == taskNum){
          flag = true;
          if(line.startsWith("[V]")){
            System.out.println("이미 완료 처리 되어있습니다.");
            return;
          }
          else{
            line = line.replace("[ ]", "[V]");
            taskList.add(line);
          }
        }
        else{
          taskList.add(line);
        }
        count++;
      }
    }catch(Exception e){
      throw new RuntimeException(e);
    }

    if(!flag){
      System.out.println("해당하는 번호의 Todo List를 찾지 못했습니다.");
      return;
    }

    try(BufferedWriter bw = new BufferedWriter(new FileWriter(TODO_DIR))){
      for(String task : taskList){
        bw.write(task);
        bw.newLine();
      }
    } catch(Exception e){
      throw new RuntimeException(e);
    }
    System.out.println("완료처리 되었습니다 : " + taskList.get(taskNum-1));
  }


  private static void cancelTask(Scanner sc){
    System.out.print("완료취소할 할 번호를 입력하세요 : ");
    int taskNum = sc.nextInt();
    sc.nextLine();

    ArrayList<String> taskList = new ArrayList<>();
    boolean flag = false;

    try(BufferedReader br = new BufferedReader(new FileReader(TODO_DIR))){
      int count = 1;
      String line;

      while((line = br.readLine()) != null){
        if(count == taskNum){
          flag = true;
          if(line.startsWith("[ ]")){
            System.out.println("이미 완료가 되어있지 않은 번호입니다.");
            return;
          }
          else{
            line = line.replace("[V]", "[ ]");
            taskList.add(line);
          }
        }
        else{
          taskList.add(line);
        }
        count++;
      }
    } catch(Exception e){
      throw new RuntimeException(e);
    }

    if(!flag){
      System.out.println("해당하는 번호의 Todo List를 찾지 못했습니다.");
      return;
    }

    try(BufferedWriter bw = new BufferedWriter(new FileWriter(TODO_DIR))){
      for(String task : taskList){
        bw.write(task);
        bw.newLine();
      }
    } catch(Exception e){
      throw new RuntimeException(e);
    }
    System.out.println("완료 취소 처리되었습니다 : " + taskList.get(taskNum-1));
  }
} // end of class