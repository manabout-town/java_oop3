package client.ch03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MultiThreadedClient {

  public static void main(String[] args) {
    // 192.168.4.12 - 회욱 IP 주소
    try (Socket socket = new Socket("192.168.4.101", 5000)) {

      // 소켓에서 연결 할 입력, 출력 스트림 2개가 필요하다.
      PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
      BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

      // 클라이언트에서 키보드에서 값을 입력 받을 스트림이 필요 하다.
      BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

      Thread readThread = new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            String serverMessage;
            while ((serverMessage = reader.readLine()) != null) {
              if ("exit".equalsIgnoreCase(serverMessage)) {
                System.out.println("서버가 종료되었습니다");
                break;
              }
            }

          } catch (IOException e) {
            System.out.println("서버와의 연결이 끊겼습니다.");
          }
        }
      });

      // 쓰기 스레드 생성
      Thread writeThread = new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            String clientMessage;
            while ((clientMessage = keyboardReader.readLine()) != null) {
              writer.println(clientMessage);
              if ("exit".equalsIgnoreCase(clientMessage)) {
                System.out.println("클라이언트가 종료했습니다");
                break;
              }
            }
          } catch (IOException e) {
            System.out.println("메세지 전송 중 오류가 발생했습니다.");
          }
        }
      });


      readThread.start();
      writeThread.start();

      readThread.join();
      writeThread.join();

    } catch (UnknownHostException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  } // end of mai
}
