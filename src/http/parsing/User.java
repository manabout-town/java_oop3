package http.parsing;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private int id;
  private String name;
  private String username;
  private String email;
  private String phone;
  private String website;
  private Address address;
  private Company company;
}




