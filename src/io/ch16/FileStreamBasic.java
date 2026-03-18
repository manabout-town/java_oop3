package io.ch16;

import java.io.File;
import java.io.FileWriter;

public class FileStreamBasic {

  public static void main(String[] args) {


  } // end of main

  public static void writeToFile(String fileName) {
    /**
     * FileWriter 는 문자 기반 출력 스트림입니다.
     * FileOutputStream과 fos.write(byte[]) 달리 write(string) 이 가능합니다
     * getBytes() 변환이 필요가 없습니다.
     */
    // append 모드를 설정하지 ㅇ낳으면 기본값이 false 이다.
    try (FileWriter writer = new FileWriter(fileName)) {
      String text = "자바 문자 기반 스트림 예제\n";
      // text.getBytes() 이거 할 필요 없이
      writer.write(text);
      writer.write("추가 문자열을 기록합니다.");
      //writer.flush(); 생략가능한
      System.out.println("파일에 텍스트를 잘 기록 하였습니다");
    } catch (Exception e) {
      System.out.println("파일 쓰기 중 오류 발생 : " + e.getMessage());
    }
  }

  public static void readFromFile(String fileName) {
    /**
     * FileReader 는 문자 기반 입력 스트림입니다.
     * read() 는 한 문자씩 읽어 유니코드 값(정수)으로 반환합니다.
     * FileInputStream과 사용법은 같지만 한글은 깨지지 않습니다.
     */


  }

}
