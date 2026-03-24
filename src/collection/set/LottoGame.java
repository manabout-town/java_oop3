package collection.set;

import java.util.*;

public class LottoGame {
  public static void main(String[] args) {
    Set<Integer> lotto = new HashSet<>();
    Random random = new Random();

    while (lotto.size() < 6) {
      int number = random.nextInt(45) + 1;
      lotto.add(number);
    }
    System.out.println("이번 주 로또 번호 : " + lotto);
    List<Integer> lottoNumber = new ArrayList<>(lotto);

    Collections.sort(lottoNumber);
    System.out.println("이번 주 로또 번호 : " + lottoNumber);
    System.out.println("총 " + lotto.size() + "개");

    // 로또 번호를 오름차순으로 정렬하시오
    // 힌트 - 배열이나 리스트 계열은 순서가 있음.





  } // end of main

} // end of class
