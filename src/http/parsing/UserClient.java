package http.parsing;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;

public class UserClient {
  public static void main(String[] args) {
    String urlString = "https://jsonplaceholder.typicode.com/users/1";
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

        User user = gson.fromJson(jsonString, User.class);
        System.out.println("------------------------");
        System.out.println(user.getId());
        System.out.println(user.getName());
        System.out.println(user.getUsername());
        System.out.println(user.getEmail());
        System.out.println(user.getAddress());
        System.out.println(user.getCompany());
      }


    } catch (IOException e) {
      System.out.println("통신 실패 : " + e.getMessage() );
    }


  } // end of main

} // end of class
