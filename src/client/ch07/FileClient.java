package client.ch07;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileClient {
  public static void main(String[] args) {
    String name = "박효균";
    try (Socket socket = new Socket("localhost", 5002)) {
      System.out.println("[" + name + "] 입장");

      PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
      BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

      // 쓰기 기능
      writer.println("하이");
      // 읽기 기능
      String message;
      message = reader.readLine();
      System.out.println(message);

    } catch (UnknownHostException e) {
      System.out.println("연결 대상을 찾을 수 없습니다.");
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }


  } // end of main


}
