package http.gson;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PostClient {
  public static void main(String[] args) {

    String urlString = "https://jsonplaceholder.typicode.com/posts/1";
    HttpURLConnection connection;

    try {
      URL url = new URL(urlString);
      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");

      int responseHttpCode = connection.getResponseCode();
      System.out.println("통신 성공 여부 확인 : " + responseHttpCode);

      try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {

        StringBuffer responseBody = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
          responseBody.append(line);
        }
        String jsonString = responseBody.toString();
        System.out.println("JSON 응답 : " + responseBody);

        Gson gson = new Gson();

        Post post = gson.fromJson(jsonString, Post.class);

        System.out.println("==============================");
        System.out.println(post.getId());
        System.out.println(post.getUserId());
        System.out.println(post.getTitle());
        System.out.println(post.getBody());

      }


    } catch (Exception e) {
      System.out.println("통신 실패 : " + e.getMessage());


    }

  } // end of main

} // end of class
