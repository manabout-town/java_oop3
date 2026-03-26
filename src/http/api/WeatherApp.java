package http.api;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;

public class WeatherApp {

  public static void main(String[] args) {

    // 1. 공공 데이터 포탈 인증키 (보통 Decoding 사용)
    String serviceKey = "cab942b8a0308a10451f39191b258eb2";
    //
    // 2. 조회에 필요한 파라미터 설정


    try {
      String urlString = "https://api.openweathermap.org/data/2.5/weather?q=Busan&appid="
          + serviceKey + "&units=metric";

      URL url = new URL(urlString);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      BufferedReader rd;
      if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
        rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      } else {
        rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
      }
      StringBuilder sb = new StringBuilder();
      String line;
      while ((line = rd.readLine()) != null) {
        sb.append(line);
      }

      String jsonString = sb.toString();
      Gson gson = new Gson();
      WeatherAll weatherAll = gson.fromJson(jsonString, WeatherAll.class);


      // 도전 과제 1 - 파싱 처리 직접
      // 출력 값
      System.out.println("▶ 상태 : " + weatherAll.getWeather().get(0).getMain());
      System.out.println("▶ 기온 : " + weatherAll.getMain().getTemp() + "°C");
      System.out.println("▶ 습도 : " + weatherAll.getMain().getHumidity() + "%");
      System.out.println("▶ 풍속 : " + weatherAll.getWind().getSpeed() + "m/s");

      if (weatherAll.getMain().getTemp() > 25) {
        System.out.println("오늘 날씨가 덥습니다");
      } else if (weatherAll.getMain().getTemp() < 10) {
        System.out.println("오늘 날씨가 춥습니다");
      } else {
        System.out.println("▶ \uD83D\uDCA1 오늘은 날씨가 따뜻하네요. 산책하기 좋아요!");
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
