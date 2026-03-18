package io.ch17;

import java.io.*;

public class CharBufferedKeyboardConsole {

  public static void main(String[] args) throws IOException {

    // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(isr);

    // BufferedWriter bw = new BufferedWriter(new PrintWriter(System.out);
    PrintWriter pw = new PrintWriter(System.out);
    BufferedWriter bw = new BufferedWriter(pw);

    // 콘솔창에 종료 명령 (Ctrl + D)
    System.out.println("텍스트를 입력하세요 (종료 : 빈줄 입력)");

    String line;

    while((line = br.readLine()) != null) {
      bw.write("받은 값 출력 : " + line);
      bw.newLine(); // 운영체제에 맞는 줄바꿈 자동 삽입
      // \n <-- 리눅스, Mac
      // \r\n <-- 윈도우
      bw.flush(); // 버퍼에 남은 데이터 즉시출력
    }




  } // end of main
}
