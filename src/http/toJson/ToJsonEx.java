//package http.toJson;
//
//import com.google.gson.Gson;
//import http.json.Post;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class ToJsonEx {
//
//  public static void main(String[] args) {
//    Post post = new Post();
//    post.setUserId(1);
//    post.setId(10);
//    post.setTitle("자바 공부");
//    post.setBody("JSON 파싱 연습");
//
//    Gson gson = new Gson();
//    String jsonPost = gson.toJson(post);
//    System.out.println(jsonPost);
//
//    Post rePost = gson.fromJson(jsonPost, Post.class);
//    System.out.println(rePost);
//
//  }
//}
