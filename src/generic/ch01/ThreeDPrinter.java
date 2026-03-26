package generic.ch01;

public class ThreeDPrinter {

  // 재료
  Object material;

  // 재료 꺼냄

  public Object getMaterial() {
    return material;
  }


  // 재료 넣기


  public void setMaterial(Object material) {
    this.material = material;
  }


  // 테스트 코드
  public static void main(String[] args) {

    Plastic plastic = new Plastic();
    Powder powder = new Powder();

    ThreeDPrinter threeDPrinter = new ThreeDPrinter();
    threeDPrinter.setMaterial(plastic);
    System.out.println(threeDPrinter.getMaterial());
    System.out.println("--------------------");
    threeDPrinter.setMaterial(powder);
    System.out.println(threeDPrinter.getMaterial());

    // 타입을 선언해서 저장 시켜보자
    Plastic tempPlastic = (Plastic) threeDPrinter.getMaterial();

    System.out.println("프로그램을 종료합니다");



  } // end of main
}
