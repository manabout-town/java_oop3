package http.api;

import collection.ex.Main;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
public class WeatherAll {
  private List<Weather> weather;
  private Main main;
  private Wind wind;

@Data
public static class Weather {
  private String main;
}
@Data
public static class Main {
  private double temp;
  private int humidity;
}
@Data
public static class Wind {
  private double speed;
}



} // end of class


