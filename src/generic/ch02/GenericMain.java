package generic.ch02;

import generic.ch01.Plastic;
import generic.ch01.Powder;

import java.sql.SQLOutput;

public class GenericMain {

  public static void main(String[] args) {
    // 제네릭 프로그래밍을 활용하면 타입에 안정성을 줘서 컴파일 시 (코드 작성시)
    // 명확하게 타입을 지정할 수 있다.
    Plastic plastic1 = new Plastic();
    Powder powder1 = new Powder();
    Water water1 = new Water();
    // 사용하는 시점에는 무엇을 넣을지 미리 지정해 주어야 한다.
    GenericPrinter<Plastic> genericPrinter = new GenericPrinter();
    // 컴파일 시점에 플라스틱을 넣기로 했는데 다른 타입이 들어가면 컴파일 오류가 발생한다.
//    genericPrinter.setMaterial(powder1);
    genericPrinter.setMaterial(plastic1);
    Plastic tempPlastic = genericPrinter.getMaterial();
    //컴파일 시점에 역시 타입을 확인할 수 있게 추가로 다운캐스팅 작업도 필요가 없다
    System.out.println(tempPlastic);
    System.out.println("----------------------------------");

    // 자료 매개변수는 무엇이든 다 담을 수 있지만 이 또한 단점들이 발생 할 수 있다.
    GenericPrinter<Water> waterGenericPrinter = new GenericPrinter<>();
    waterGenericPrinter.setMaterial(water1);
    System.out.println(waterGenericPrinter.getMaterial());


  } // end of main
}
