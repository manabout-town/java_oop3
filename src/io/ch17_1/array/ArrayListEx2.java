package io.ch17_1.array;

import java.util.ArrayList;
import java.util.List;

public class ArrayListEx2 {
  // 정수 실수 불리언 사용자 정의 객체를 담을 수 있는 ArrayList 각각 만들어서 사용해 보기
  public static void main(String[] args) {

    ArrayList<Integer> intList = new ArrayList<>();
    intList.add(10);
    intList.add(20);
    intList.add(30);
    intList.add(40);


    System.out.println("정수 리스트 : " + intList);

    // 2. 실수를 담는 리스트
    ArrayList<Double> doubleList = new ArrayList<>();
    doubleList.add(1.0);
    doubleList.add(2.0);
    doubleList.add(3.0);
    System.out.println(doubleList);


    // 3. 불리언 담을 리스트
    ArrayList<Boolean> booleanList = new ArrayList<>();
    booleanList.add(true);
    booleanList.add(false);

    // 4. 사용자 정의 객체를 담는 리스트
    ArrayList<Book2> book2List = new ArrayList<>(); // 최대 갯수는 10로잡히고 11개가 되면 10개 단위로 늘어남
    book2List.add(new Book2("자바책"));
    book2List.add(new Book2("RDBMS책"));

    System.out.println(book2List.get(0));
    System.out.println(book2List.get(1));
    try {
      System.out.println(book2List.get(2));
    } catch (Exception e) {
      e.printStackTrace();
    }


    System.out.println("프로그램 정상 종료");

  } // end of main
}

class Book2 {

  String title;

  public Book2(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return  "[title=" + title + "]";
  }
}

