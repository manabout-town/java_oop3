package http.json;

public class NotJsonEx2 {

  public static void main(String[] args) {

    String json = "{\"userId\": 1,\"id\": 1,\"title\":\"xquidem molestiae enim\"}";
    // JSON 혀식에 문자열을 파싱해서 Album 객체로 변환해보자

    String step1 = json.replace("{", "").replace("}", "");
    System.out.println(step1);

    String[] parts= step1.split(",");
    System.out.println(parts.length);
    System.out.println(parts[0]);
    System.out.println(parts[1]);
    System.out.println(parts[2]);
    // userId : 1
    String userIdV = parts[0].split(":")[1];
//    String userId = userIdV[0];
//    String userIdValue = userIdV[1];
//    String  userIdV = (parts[0].split(":"))[1];



    int userIdValue = Integer.parseInt(userIdV.trim());
    System.out.println(userIdValue);

    String idV = parts[0].split(":")[1];
    int idValue = Integer.parseInt(idV.trim());
    System.out.println(idValue);

    String titleV = parts[0].split(":")[1];
    System.out.println(titleV);

    Album album = new Album(userIdValue, idValue, titleV);
    System.out.println(album.toString());

  } // end of main

} // end of class

