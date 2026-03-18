package io.ch17_1.array;

import java.util.ArrayList;

public class ArrayListEx {

  public static void main(String[] args) {

    ArrayList<String> list = new ArrayList<>();

    list.add("사과"); // 내부적으로 [0] 인덱스 자동 저장
    list.add("바나나");
    list.add("포도");

    // 조회하는기능
    System.out.println(list.get(0));
    // System.out.println(list.get(100)); <- 오류 발생
    System.out.println(list.getFirst());
    System.out.println(list.getLast());

    // 삭제하는기능
//    list.remove("바나나");
//    list.remove(0);

    System.out.println(list.get(0));

    System.out.println("현재 arrayList 에 길이 : " + list.size());
    System.out.println(list.isEmpty());
    list.clear();
    System.out.println(list.isEmpty());

  } // end of main

}
