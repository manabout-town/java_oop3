package io.ch14;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyFileOutputSystem {

  public static void main(String[] args) {
    String data = "Hello Java, FileOutputSystem abc abc 안녕 반가워";
    // 파일이 없으면 새로 생성, 있으면 덮어 쓰기
    //(FileInputStream fos = new FileOutputStream("output.txt")

    // (FileInputStream fos = new FileOutputStream("output.txt", true) --> Append 모드
    // 기존 파일에 내용이 있다면 true 뒤에 이어쓰기, false -> 덮어 쓰기

    try (FileOutputStream fos = new FileOutputStream("output.txt", false)) {
      // 문자열은 FileOutputStream 으로 직접 쓸 수 없습니다.
      // 문자열을 -> byte 배열로 변환해서 넣어 주어야 합니다.

      // 문자열을 바이트 배열로 변환해서 임시 변수에 담아 둠
      byte[] dataBytes = data.getBytes();
      fos.write(dataBytes);

      // fos.flush();
      System.out.println("파일 출력 완료 : output.txt");

    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (Exception e){
      throw new RuntimeException(e);
    }
    // 참고 : output.txt 를 에디터로 열면 텍스트가 보입니다.
    // 에디터가 바이트 데이터를 문자로 해석해서 보여주기 때문에
    // FileOutputSystem 으로 바이트를 썻지만, 보여주는 방식은 에디터가 결정합니다.


    /**
     * 1/4
     * 2/2
     * 3/4
     * 4/2
     * 5/1
     * 6/
     */
  }
}
