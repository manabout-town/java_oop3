package server.ch07;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Scanner;

public class FileServer {

  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(5002)) {
      System.out.println("연결 대기중...");
      serverSocket.accept();
      System.out.println("연결 완료");
      Socket socket = new Socket();

      PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
      BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

      // 기능 생성 (쓰기)
      writer.println("안녕");
      // 기능 생성 (읽기)
      String message;
      message = reader.readLine();
      System.out.println(message);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
