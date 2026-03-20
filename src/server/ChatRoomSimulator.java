package server;

import java.util.Vector;

public class ChatRoomSimulator {

  private  static Vector<String> userList = new Vector<>();

  public static void main(String[] args) throws InterruptedException {
    // ArrayList 사용법 동일

//    Vector<String> list = new Vector<>();
//
//    list.add("철수");
//    list.add("영희");
//    list.remove("영회");
//    list.get(0);
//    list.size();
//    list.contains("영희");
//
//    for (String name : list) {
//      System.out.println(name);
//    }

    System.out.println("===채팅방 접속자 시뮬레이션===");

    // 람다 표현식 ->
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        userList.add("철수");
        System.out.println("[접속] 철수 |  현재 " + userList.size() + "명");
      }
    });

    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        userList.add("영희");
        System.out.println("[접속] 영희 |  현재 " + userList.size() + "명");
      }
    });

    Thread t3 = new Thread(new Runnable() {
      @Override
      public void run() {
        userList.add("민준");
        System.out.println("[접속] 민준 |  현재 " + userList.size() + "명");
      }
    });



    t1.start();
    t2.start();
    t3.start();

    t1.join();
    t2.join();
    t3.join();



    System.out.println("최종 접속자 : " + userList);

    // 영희 퇴장
    userList.remove("영희");
    System.out.println("영희 퇴장 후 : " + userList);

  } // end of main
}
