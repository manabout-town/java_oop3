package http.api;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class ApiExplorer {
  public static void main(String[] args) throws IOException {
    StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo"); /*URL*/
    urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=c7f2b910bbd2500c85bf2f8b1a922da96316c8c47e1a4a10602cc6d7ae3b6cc1"); /*Service Key*/
    urlBuilder.append("&" + URLEncoder.encode("returnType", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*xml 또는 json*/
    urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*한 페이지 결과 수*/
    urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
    urlBuilder.append("&" + URLEncoder.encode("year", "UTF-8") + "=" + URLEncoder.encode("2025", "UTF-8")); /*측정 연도*/
    urlBuilder.append("&" + URLEncoder.encode("itemCode", "UTF-8") + "=" + URLEncoder.encode("PM10", "UTF-8")); /*미세먼지 항목 구분(PM10, PM25), PM10/PM25 모두 조회할 경우 파라미터 생략*/
    URL url = new URL(urlBuilder.toString());
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Content-type", "application/json");
    System.out.println("Response code: " + conn.getResponseCode());
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
    rd.close();
    conn.disconnect();


  }
}