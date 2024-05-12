import java.util.Scanner;

// Still need to fix null errors on operations after a name is deleted from the the network.

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SocialNetwork network = new SocialNetwork();
        boolean exit = false;

        while (!exit) {
            System.out.println("\nWelcome to the Social Network Manager");
            System.out.println("1. Load a new network");
            System.out.println("2. List all friends of a member");
            System.out.println("3. List all friends and friends' friends of a member");
            System.out.println("4. List common friends of two members");
            System.out.println("5. Delete a member from the network");
            System.out.println("6. List all members sorted by popularity");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter the path for the index file: ");
                    String indexFile = scanner.nextLine();
                    System.out.print("Enter the path for the friend file: ");
                    String friendFile = scanner.nextLine();
                    network.loadNetwork(indexFile, friendFile);
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
                    break;
                default:
                    System.out.println("Invalid option, please choose again.");
                    break;
            }
        }

        scanner.close(); // Close scanner only once at the end of all operations
        System.out.println("Exiting program...");
    }
}
