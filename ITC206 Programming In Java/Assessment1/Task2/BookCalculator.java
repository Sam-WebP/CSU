package Task2;
import java.util.Scanner;

public class BookCalculator {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int totalBill = 0;
        char response = 'y';

        System.out.println("\nWelcome to the Annual Australian Book Festival!");

        do {
            System.out.print("\nEnter the number of large print hardback books purchased: ");
            int largeQty = input.nextInt();

            System.out.print("Enter the number of small print hardback books purchased: ");
            int smallQty = input.nextInt();

            System.out.print("Enter the number of softcover books purchased: ");
            int softQty = input.nextInt();
            input.nextLine();

            int largeDealQty = largeQty / 2;
            int smallDealQty = smallQty / 3;
            int softDealQty = softQty / 4;

            int largeStandardQty = largeQty % 2;
            int smallStandardQty = smallQty % 3;
            int softStandardQty = softQty % 4;
            
            int largeTotal = (largeDealQty * 30) + (largeStandardQty * 20);
            int smallTotal = (smallDealQty * 20) + (smallStandardQty * 10);
            int softTotal = (softDealQty * 15) + (softStandardQty * 5);

            int bill = largeTotal + smallTotal + softTotal;
            
            totalBill += bill;

            System.out.println("Your total bill is: $" + bill + "\n");

            System.out.print("Would you like to calculate another bill (y/n)? ");
            response = input.nextLine().charAt(0);

        } while (response != 'n');

        System.out.print("\nTotal sales in this session: $" + totalBill);
        System.out.print("\nGoodbye!\n");

    }

}
