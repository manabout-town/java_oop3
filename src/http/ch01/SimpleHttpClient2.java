package http.ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

// https://jsonplaceholder.typicode.com/todos/1
public class SimpleHttpClient2 {

  public static void main(String[] args) {

   // 자바로 HTTP 요청과 응답 만들어보기
    String urlString = "https://jsonplaceholder.typicode.com/users/10";
    HttpURLConnection connection = null;

    try {
      URL url = new URL(urlString);
      connection = (HttpURLConnection)url.openConnection();

      connection.setRequestMethod("GET");
      connection.setRequestProperty("Accept", "application/json");

      int responseCode = connection.getResponseCode();
      System.out.println("응답 코드 확인 : " + responseCode);

      if (responseCode != 200) {
        System.out.println("요청 실패!");
        return;
      }

      try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
        StringBuffer response = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
          response.append(line).append("\n");
        }
        System.out.println("응답 내용 :");
        System.out.println(response.toString());

      }

    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  } // end of main

}
