package http.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class PostClient2 {
  public static void main(String[] args) {

    String urlString = "https://jsonplaceholder.typicode.com/posts";
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
        while ( (line = reader.readLine()) != null) {
          responseBody.append(line);
        }
        String jsonString = responseBody.toString();
        System.out.println("JSON Array 응답 : " + responseBody);

        Gson gson = new Gson();
        TypeToken<List<Post>> typeToken = new TypeToken<>() {
        };
//        List<Post> postList = gson.fromJson(jsonString, typeToken.getRawType());
//
//        System.out.println("전체 개수 : " + postList.size() + "개");
//        for (int i = 0; i < postList.size(); i++) {
//          System.out.println(postList.get(i));
//
//        }

      }


    } catch (Exception e) {
      System.out.println("통신 실패 :" + e.getMessage());
    }


  } // end of main

} // end of class
