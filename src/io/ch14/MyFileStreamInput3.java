package io.ch14;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class MyFileStreamInput3 {
  public static void main(String[] args) {

//    try (Scanner sc = new Scanner(System.in)) {
//      System.out.print("이름 입력 : ");
//      String name = sc.nextLine();
//      }
     try (Scanner sc = new Scanner(System.in)) {
       try {
         System.out.println("이름 입력 :");
         String name = sc.nextLine();
       } finally {
        sc.close();
       }
     }


  }
}
