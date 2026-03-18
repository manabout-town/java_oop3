package io.ch14;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyFileStreamInput {
  public static void main(String[] args) {

    // 파일을 바이트 단위로 읽어들이는 스트림
    FileInputStream in = null;
    int readData;

    try {
      in = new FileInputStream("a.txt");

      // 첫번째 바이트 읽기
      readData = in.read();
      System.out.println("readData : " + readData);
      System.out.println("readData : " + (char)readData);
      //두번쨰 바이트 읽기
      readData = in.read();
      System.out.println("readData : " + readData);
      System.out.println("readData : " + (char)readData);
      //세번째 바이트 읽기
      readData = in.read();
      System.out.println("readData : " + readData);
      System.out.println("readData : " + (char)readData);

    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }



    // 주의 여시고 in.close() 를 하지 않으면
    // 즉, 스트림을 닫지 않으면 파일이 계속 열린 상태로 메모리 누수가 생깁니다.


  }

}
