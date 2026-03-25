package http.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Items {
  private int clearVal;
  private int sn;
  private String districtName;
  private String dateDate;
  private int issueVal;
  private String issueTime;
  private String clearDate;
  private String issueDate;
  private String moveName;
  private String clearTime;
  private String issueGbn;
  private String itemCode;


}
