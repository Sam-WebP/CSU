import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The Main class represents the command-line interface for users to interact with the social network.
 */
public class Main {
    /**
     * The main method creates a Scanner object to read user input and a SocialNetwork object to manage the network.
     * A menu of options are presented to the user to execute the selected option until the user chooses to exit.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SocialNetwork network = new SocialNetwork();
        boolean exit = false;

        // Main loop to display menu and handle user input until user chooses to exit
        while (!exit) {
            // Display menu options
            System.out.println("\nWelcome to the Social Network Manager");
            System.out.println("1. Load a new network");
            System.out.println("2. List all friends of a member");
            System.out.println("3. List all friends and friends friends of a member");
            System.out.println("4. List common friends of two members");
            System.out.println("5. Delete a member from the network");
            System.out.println("6. List all members sorted by popularity");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = scanner.nextInt(); // Read user's choice
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer option."); // Handle invalid input (non-integer value)
                scanner.nextLine(); // Clear the invalid input from the scanner
                continue; // Restart the loop
            }

            scanner.nextLine();

            // Execute the selected option based on user's choice by running the relevant prompt method
            switch (choice) {
                case 1:
                    network.promptToLoadNetwork(scanner);
                    break;
                case 2:
                    network.promptToFindFriends(scanner);
                    break;
                case 3:
                    network.promptToFindFriendsOfFriends(scanner);
                    break;
                case 4:
                    network.promptToFindCommonFriends(scanner);
                    break;
                case 5:
                    network.promptToDeleteMember(scanner);
                    break;
                case 6:
                    network.printAllMembers();
                    break;
                case 7:
                    exit = true;
                    break;// Exit the application
                default:
                    // Handle invalid option
                    System.out.println("Invalid option, please choose again.");
                    break;
            }
        }

        scanner.close();
        System.out.println("Exiting program...");
    }
}