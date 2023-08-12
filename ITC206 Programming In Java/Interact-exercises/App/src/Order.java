import java.util.Scanner;

public class Order {
 public static void main(String[] argv) {
         Scanner input = new Scanner(System.in);

         System.out.print("Enter the first number: ");
         int number1 = input.nextInt();
         System.out.print("Enter the second number: ");
         int number2 = input.nextInt();
         System.out.print("Enter the third number: ");
         int number3 = input.nextInt();

         if (number1 < number2 && number1 < number3 && number2 < number3) {
                System.out.println(number1 + ", " + number2 + ", " + number3);
         } else if (number1 < number2 && number1 < number3 && number3 < number2) {
                System.out.println(number1 + ", " + number3 + ", " + number2);

         } else if (number2 < number1 && number2 < number3 && number3 < number1) {
                System.out.println(number2 + ", " + number3 + ", " + number1);
         } else if (number2 < number1 && number2 < number3 && number1 < number3) {
                System.out.println(number2 + ", " + number1 + ", " + number3);
         
         } else if (number3 < number1 && number3 < number2 && number2 < number1) {
                System.out.println(number3 + ", " + number2 + ", " + number1);
         } else if (number3 < number1 && number3 < number2 && number1 < number2) {
                System.out.println(number3 + ", " + number1 + ", " + number2);
         }
    }
}