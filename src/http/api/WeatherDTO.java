package http.api;

import java.util.List;

public class WeatherDTO {
  private Response response;


  public static class Response {
    private Header header;
    private Body body;
  }

  public static class Header {
    private String resultCode;
    private String resultMsg;
  }

  public static class Body {
    private String dataType;
    private Items items;

    public static class Items {
      private List<Item> item;
    }

    public static class Item {
      private String baseDate; // 발표 일자
      private String baseTime; // 발표 시각
      private String category; // 자료 구분 코드
      private String obsrValue; //
      private int nx;
      private int ny;
    }

  }
}
