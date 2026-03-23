package client.ch06;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class FileClient {
  public static void main(String[] args) {
    String serverIp = "127.0.0.1"; // 같은 컴퓨터면 localhost 가능
    int port = 5001;

    String filePath = "todo.txt"; // 보낼 파일 경로

    File file = new File(filePath);

    if (!file.exists()) {
      System.out.println("파일이 존재하지 않습니다.");
      return;
    }

    try (
        Socket socket = new Socket(serverIp, port);
        FileInputStream fis = new FileInputStream(file);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream())
    ) {
      System.out.println("서버 접속 성공");

      // 1. 파일 이름 보내기
      dos.writeUTF(file.getName());

      // 2. 파일 크기 보내기
      dos.writeLong(file.length());

      // 3. 파일 내용 보내기
      byte[] buffer = new byte[1024];
      int readBytes;

      while ((readBytes = fis.read(buffer)) != -1) {
        dos.write(buffer, 0, readBytes);
      }

      dos.flush();

      System.out.println("파일 전송 완료");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}