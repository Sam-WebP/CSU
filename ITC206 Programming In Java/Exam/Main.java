package Exam;

import java.util.Date;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {

        for (int i = 0;i < 16; i+=3) {
            for (int j = 1;j<i; j++) {
              System.out.print("v");
               j++;
            }
            System.out.println();
          }   
    }
    
}
