package server.ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer {

  public static void main(String[] args) {

    try (ServerSocket serverSocket = new ServerSocket(5001)) {
      System.out.println("클라이언트에 연결 요청을 기다립니다...");
      Socket clientSocket = serverSocket.accept();
      System.out.println("==========서버 실행 =============");

      /// // 소켓과 연결 된 스트림
      BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
      /// //

      /// 키보드와 연결할 스트림
      BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

      // 읽기 스레드 : 클라이언트 메세지를 계속 수신
      Thread readThread = new Thread(new Runnable() {
        @Override
        public void run() {
          while (true) {
          try {
            String clientMessage;
            while ( (clientMessage = reader.readLine() ) != null ) {
              if ("exit".equalsIgnoreCase(clientMessage)) {
                System.out.println("클라이언트가 종료했습니다");
                break;
              }
            }

          }catch (IOException e) {
            throw new RuntimeException(e);
          }
          }
        }
      });

      Thread writeThread = new Thread(new Runnable() {
        @Override
        public void run() {

          try {
            String serverMessage;
            while ((serverMessage = keyboardReader.readLine()) != null) {
              writer.println(serverMessage);
              if ("exit".equalsIgnoreCase(serverMessage)) {
                System.out.println("서버가 종료되었습니다");
                break;
              }
            }
          }catch (IOException e) {
            throw new RuntimeException(e);
          }
        }
      });

      readThread.start();
      writeThread.start();

      readThread.join();
      writeThread.join();
      /**
       * join() = 이 스레드가 끝날 때 까지 기다려줘 라는 의미 이다.
       * Thread.sleep() 이 "N초 동안 잠깐 잠들어 멈춰" 라면
       * join() 저 스레드가 잠들때까지 멈춰
       *
       * join() 이 없으면
       * main 스레드 바로 try 블록을 벗어남
       * 소켓 자동 close()
       * 아직 실행 중인 readThread.writeThread 가 닫힌 소켓으로 통신 시도
       */


      String line;
      while ( (line = reader.readLine())!= null) {
        // 클 ---> exit <--- EXIT
        // equalsIgnoreCase 대소문자 구분없이 값 확인
        if("exit".equalsIgnoreCase(line)) {
          break; // while 종료
        }
        System.out.println("클라이언트 > " + line);

        // 서버 측 컴퓨터의 키보드에서 값을 받아서 클라이언트 측으로 전송
        System.out.print("서버 입력 > ");
        // ** 서버에서 응답을 받아야만 메세지를 보낼 수 있음  **
        String severMsg = keyboardReader.readLine(); // 블로킹 상태 (콘솔창에 글 입력해야 함)
        // 클라이언트와 연결 된 소켓 출력 스트림을 활용해서 내용을 전달 한다.
        writer.println(severMsg);
        // 서버측에서도 더이상 글 입력 받기 싫다면 exit 입력으로 while 문 종료 처리
        if ("exit".equalsIgnoreCase(severMsg)) {
          break;
        }
      }

    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }

  } // end of main

} // end of class
