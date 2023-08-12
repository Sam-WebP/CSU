import java.util.Scanner;

public class Match {
 public static void main(String[] argv) {
         Scanner input = new Scanner(System.in);

         System.out.print("Enter the first number: ");
         int number1 = input.nextInt();
         System.out.print("Enter the second number: ");
         int number2 = input.nextInt();

         if (number1 > number2) {
                System.out.println("FIRST");
         } else if (number2 > number1) {
                System.out.println("SECOND");
         } else if (number1 == number2 && number1 != 0) {
                System.out.println("EQUAL");
         } else {
                System.out.println("BOTH ZERO");
         }
    }
}