// package Assessment2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SocialNetwork network = new SocialNetwork();
        network.loadNetwork("index.txt", "friend.txt");

        Scanner scanner = new Scanner(System.in);
        //System.out.print("Enter a name to list their friends: ");
        System.out.print("Enter a name to list their friends and friends' friends: ");
        String name = scanner.nextLine();
        
        // network.listFriends(name);
        network.listExtendedNetwork(name);
        scanner.close();
        // For testing: print adjacency matrix
        // int[][] matrix = network.getGraph().getAdjacencyMatrix();
        // for (int i = 0; i < matrix.length; i++) {
        //     for (int j = 0; j < matrix[i].length; j++) {
        //         System.out.print(matrix[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        
    }
}
