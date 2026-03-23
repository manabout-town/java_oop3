package server.ch06;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class FileServer {
  public static void main(String[] args) {
    int port = 5001;

    try (ServerSocket serverSocket = new ServerSocket(port)) {
      System.out.println("서버 실행 중...");
      System.out.println("클라이언트 접속 대기 중...");

      Socket socket = serverSocket.accept();
      System.out.println("클라이언트 접속 성공: " + socket.getInetAddress());

      DataInputStream dis = new DataInputStream(socket.getInputStream());

      // 1. 파일 이름 받기
      String fileName = dis.readUTF();

      // 2. 파일 크기 받기
      long fileSize = dis.readLong();

      // 3. 저장할 파일 만들기
      FileOutputStream fos = new FileOutputStream("받은_" + fileName);

      byte[] buffer = new byte[1024];
      int readBytes;
      long totalRead = 0;

      // 4. 파일 내용 받기
      while (totalRead < fileSize) {
        readBytes = dis.read(buffer);

        if (readBytes == -1) {
          break;
        }

        fos.write(buffer, 0, readBytes);
        totalRead += readBytes;
      }

      fos.close();
      dis.close();
      socket.close();

      System.out.println("파일 수신 완료: 받은_" + fileName);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}