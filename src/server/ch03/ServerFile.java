package server.ch03;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFile{

  // volatile : 여러 스레드가 같은 변수를 볼 때 항상 최신 값을 보게 해주는 키워드
  static volatile boolean getMessage = true;
  static volatile boolean sendResponse = true;
  static Thread getMessageThread;

  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(3000)) {

      System.out.println("서버 시작 - 포트 번호 3000");
      Socket clientSocket = serverSocket.accept();

      // 간단한 흐름 약속( 연결 후 클라이언트가 먼저 서버측으로 메시지를 보낼 예정)

      // 읽기 스트림 준비 (client --> server)
      BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
      // 쓰기 스트림 준비 (server --> client)
      PrintWriter writer = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

      // 키보드에서 쓰기 스트림
      BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

      if(clientSocket.isConnected()){
        System.out.println("클라이언트 연결 성공");
        getMessage(reader);
        sendResponse(writer, keyboardReader);

        getMessageThread.join(); // 쓰레드 종료 대기
        // Why? : 프로그램 안전하게 종료하기 위해
      }

      System.out.println("프로그램이 안전하게 종료됩니다.");

    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private static void sendResponse(PrintWriter writer, BufferedReader keyboardReader){
    Thread sendResponseThread = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          while(sendResponse){
            String response = keyboardReader.readLine();

            if(response == null) break;

            if(response.equals("exit")){
              writer.println("exit");
              sendResponse = false;
              break;
            }

            writer.println(response);
          }
        } catch (IOException e) {
          System.out.println("입력 중 오류 발생");
        }
      }
    });
    // setDaemon(true) : 메인 종료시 같이 종료
    sendResponseThread.setDaemon(true);
    sendResponseThread.start();
  }

  private static void getMessage(BufferedReader reader){
    getMessageThread = new Thread(new Runnable() {
      @Override
      public void run(){
        while(getMessage){
          try {
            String message = reader.readLine();
            if(message == null || message.equals("exit")){
              getMessage = false;
              sendResponse = false;
              System.out.println("종료 코드 입력 감지 !");
              break;
            }
            System.out.println("클라이언트 : " + message);
          } catch (IOException e) {
            break;
          }
        }
      }
    });
    getMessageThread.start();
  }
}