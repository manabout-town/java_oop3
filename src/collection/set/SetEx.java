package collection.set;

import java.util.HashSet;
import java.util.Set;

public class SetEx {

  public static void main(String[] args) {

    Set<String> set = new HashSet<>();

    // 추가
    set.add("철수");
    set.add("영희");
    set.add("철수"); // 중복! 무시처리됨

    System.out.println(set);
    System.out.println(set.size());

    // 포함여부
    System.out.println(set.contains("철수"));
    System.out.println(set.contains("민준"));
//    set.contains("철수");
//    set.contains("민준");
    set.remove("철수");




  } // end of main

} // end of class
