package client.ch03;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientFile{

  static volatile boolean getResponse = true;
  static volatile boolean sendMessage = true;
  static Thread getResponseThread;

  public static void main(String[] args) {
    try (Socket socket = new Socket("192.168.7.154", 3000)) {

      // 소켓에서 쓰기 스트림
      PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
      // 소켓에서 읽기 스트림
      BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

      // 키보드에서 쓰기 스트림
      BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

      if(socket.isConnected()){
        System.out.println("서버가 연결되었습니다.");
        getResponse(reader);
        sendMessage(writer, keyboardReader);

        getResponseThread.join();
      }

      System.out.println("프로그램이 안전하게 종료됩니다.");

    } catch (UnknownHostException e) {
      System.out.println("서버측을 알 수 없습니다." + e.getMessage());
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private static void sendMessage(PrintWriter writer, BufferedReader keyboardReader){
    Thread sendMessageThread = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          while(sendMessage){
            String message = keyboardReader.readLine();

            if(message == null) break;

            if(message.equals("exit")){
              writer.println("exit");
              sendMessage = false;
              break;
            }

            writer.println(message);
          }
        } catch (IOException e) {
          System.out.println("입력 중 오류 발생");
        }
      }
    });
    sendMessageThread.setDaemon(true);
    sendMessageThread.start();
  }

  private static void getResponse(BufferedReader reader){
    getResponseThread = new Thread(new Runnable() {
      @Override
      public void run() {
        while(getResponse){
          try {
            String response = reader.readLine();
            if(response == null || response.equals("exit")){
              getResponse = false;
              sendMessage = false;
              System.out.println("종료 코드 입력 감지 !");
              break;
            }
            System.out.println("서버 : " + response);
          } catch (IOException e) {
            break;
          }
        }
      }
    });
    getResponseThread.start();
  }
}