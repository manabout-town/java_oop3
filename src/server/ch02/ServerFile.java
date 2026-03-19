package server.ch02;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerFile {
  public static void main(String[] args) {

    BufferedReader in = null;
    PrintWriter out = null;

    ServerSocket serverSocket = null;
    Socket socket = null;
    Scanner scanner = new Scanner(System.in);

    try {
      serverSocket = new ServerSocket(3000);

      System.out.println("서버실행... / 클라이언트 연결대기중...");
      socket = serverSocket.accept();

      System.out.println("클라이언트 연결됨");
      in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream());

      while (true) {
        String inputMessage = in.readLine();
        if ("quit".equalsIgnoreCase(inputMessage)) break;

        System.out.println("From Client : " + inputMessage);
        System.out.println("전송하기 >>> ");

        String outputMessage = scanner.nextLine();
        out.println(outputMessage);
        out.flush();
        if ("quit".equalsIgnoreCase(outputMessage)) break;
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    } finally {
      try {
        scanner.close();
        socket.close();
        serverSocket.close();
        System.out.println("연결종료");
      } catch (IOException e) {
        System.out.println("socket close error");
      }
    }


  }

}
