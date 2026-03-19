package client.ch01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientFile {
  public static void main(String[] args) {

    try (Socket socket = new Socket("192.168.7.154", 3000)) {

      PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
//      writer.write("Hello ~ Server~~~" + "\n");

      writer.println("ㅎㅎ");


    } catch (UnknownHostException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }
}
