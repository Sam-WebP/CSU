package Task2;
import java.util.Scanner;

public class BookCalculator {

    public static void main(String[] args) {
        // Scanner to read user input
        Scanner input = new Scanner(System.in);

        // totalBill variable to keep track of total bill amount.
        int totalBill = 0;
        // response character is used to control the do-while loop
        char response = 'y';

        // Welcome message
        System.out.println("\nWelcome to the Annual Australian Book Festival!");

        // do-while loop runs until the user enters 'n' to stop calculating new bills.
        do {
            // Number of large print hardback books purchased prompt.
            System.out.print("\nEnter the number of large print hardback books purchased: ");
            int largeQty = input.nextInt();

            // Number of small print hardback books purchased prompt.
            System.out.print("Enter the number of small print hardback books purchased: ");
            int smallQty = input.nextInt();

            // Number of softcover books purchased prompt.
            System.out.print("Enter the number of softcover books purchased: ");
            int softQty = input.nextInt();
            input.nextLine();

            // Calculate number of discounted sets of books purchased
            int largeDealQty = largeQty / 2;
            int smallDealQty = smallQty / 3;
            int softDealQty = softQty / 4;

            // Calculate number of books purchased at the standard price
            int largeStandardQty = largeQty % 2;
            int smallStandardQty = smallQty % 3;
            int softStandardQty = softQty % 4;
            
            // Calculate total cost for each type of book
            int largeTotal = (largeDealQty * 30) + (largeStandardQty * 20);
            int smallTotal = (smallDealQty * 20) + (smallStandardQty * 10);
            int softTotal = (softDealQty * 15) + (softStandardQty * 5);

            // Calculate total bill for this purchase
            int bill = largeTotal + smallTotal + softTotal;
            
            // Add current bill to the total bill
            totalBill += bill;

            // Display total bill
            System.out.println("Your total bill is: $" + bill + "\n");

            // Ask user to choose if they want to calculate another bill
            System.out.print("Would you like to calculate another bill (y/n)? ");
            response = input.nextLine().charAt(0);

            // Continue the loop when response does not equal 'n'
        } while (response != 'n');

        // Display the total sales for the session
        System.out.println("\nTotal sales in this session: $" + totalBill);
        System.out.println("\nGoodbye!");

    }

}
