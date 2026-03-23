package server.ch05;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleFileServer {
  private static final int PORT = 5003;
  private static final String UPLOAD_DIR = "Uploads/";

  public static void main(String[] args) {
    // 저장 폴더 생성 하는 법
    new File(UPLOAD_DIR).mkdir(); // 저장 폴더 생성
    System.out.println("파일 서버 시작 - 포트 : " + PORT);
    try (ServerSocket serverSocket = new ServerSocket(PORT);
         Socket clientSocket = serverSocket.accept();
    ) {
      System.out.println("클라이언트 연결 됨");
      handleClient(clientSocket);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  } // end of main

  private static void handleClient(Socket socket) {

    try (InputStream in = socket.getInputStream();
         OutputStream out = socket.getOutputStream()) {
      // 1 : 파일 이름 수신 (고정 100바이트)
      byte[] nameBuffer = new byte[100];
      in.read(nameBuffer); // 한번에 100바이트 읽어
      // 이 다음부터 아래에서 다시 in.read() 는 파일 내용을 읽음

      String fileName = new String(nameBuffer).trim();
      // tirm() : 뒤에 붙은 공간 (0바이트) 제거
      System.out.println("수신할 파일 이름 : " + fileName);

      // 2 : 파일 내용 수신 후 서버 측 컴퓨터에 저장
      File file = new File(UPLOAD_DIR + fileName);
      try (FileOutputStream fos = new FileOutputStream(file)) {
        // 클라이언트가 보낸 데이터를 읽어서 서버측 컴퓨터에 저장해야 함.
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = in.read(buffer)) != -1) {
          fos.write(buffer, 0, bytesRead); // 읽은 만큼만 저장해
        }
      }

      System.out.println("파일 저장 완료 : " + UPLOAD_DIR + fileName);
      // 클라이언트 측에 완료 메세지 전송
      out.write(("파일 업로드 성공 : " + fileName).getBytes());
      out.flush();

    } catch (IOException e) {
      throw new RuntimeException(e);
    }


  } // end of handleClient


} // end of class
