package io.ch14;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyFileStreamInput2 {
  public static void main(String[] args) {

    // 주의 a.txt 파일에서 바이트 단위로 데이터를 읽어서 콘솔에 출력할 예정
    // 한글이 있다면 깨짐 발생

    try {
      FileInputStream in = new FileInputStream("a.txt");
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

//    try() {
//
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//    }


  }

}
