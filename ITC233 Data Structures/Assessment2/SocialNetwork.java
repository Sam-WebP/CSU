import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Social network as a graph where nodes are individuals and edges are the connections between them.
 * This class provides methods to load the network data from files and to access and manipulate the graph.
 */
public class SocialNetwork {
    private Graph graph;  // The graph representing the social network

    /**
     * Constructs an empty social network.
     * The graph is initially created with no vertices and will be adjusted as needed.
     */
    public SocialNetwork() {
        this.graph = new Graph(0);
    }

    /**
     * Loads network data from the index and friend files.
     * 
     * @param indexFile File path that contains the vertex data.
     * @param friendFile File path that contains the edge data.
     */
    public void loadNetwork(String indexFile, String friendFile) {
        try {
            loadIndexFile(indexFile);
            loadFriendFile(friendFile);
        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
        }
    }

    /**
     * Uses the index file to load the vertices and their labels and transfers the vertices to the graph.
     * Each line of the index file after the first is expected to contain a vertex index followed by its label.
     * 
     * @param indexFile The file path for the index file to be read.
     * @throws IOException If an I/O error occurs reading from the file.
     */
    private void loadIndexFile(String indexFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(indexFile));
        int vertexCount = Integer.parseInt(reader.readLine().trim());
        this.graph = new Graph(vertexCount);  // Reset the graph with the correct number of vertices

        for (int i = 0; i < vertexCount; i++) {
            String line = reader.readLine().trim();
            String[] parts = line.split(" ");
            int vertexIndex = Integer.parseInt(parts[0]);
            String vertexName = parts[1];
            graph.setLabel(vertexIndex, vertexName);
        }
        reader.close();
    }

    /**
     * Friendship connections get read from the friend file and edges are added to the graph.
     * Each line of the friend file after the first is a pair of vertex indices that are connected by an edge.
     * 
     * @param friendFile The file path for the friend file to be read.
     * @throws IOException If an I/O error occurs reading from the file.
     */
    private void loadFriendFile(String friendFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(friendFile));
        int numberOfEdges = Integer.parseInt(reader.readLine().trim());

        for (int i = 0; i < numberOfEdges; i++) {
            String line = reader.readLine().trim();
            String[] parts = line.split(" ");
            int vertex1 = Integer.parseInt(parts[0]);
            int vertex2 = Integer.parseInt(parts[1]);
            graph.addEdge(vertex1, vertex2);
            graph.addEdge(vertex2, vertex1); // Add the edge in both directions for undirected graph
        }
        reader.close();
    }

    /**
     * @return The graph object populated with vertices and edges.
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * Prints the adjacency matrix of the graph for testing purposes.
     * Each cell (i, j) in the matrix contains 1 if there is an edge from vertex i to vertex j, otherwise 0.
     */
    public void printAdjacencyMatrix() {
        int size = graph.size();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print((graph.isEdge(i, j) ? 1 : 0) + " "); // Print 1 the edge is between vertex i and j, if not, print 0
            }
            System.out.println();
        }
    }

    /**
     * Finds the index of a vertex by its name.
     * 
     * @param name The name of the vertex to find.
     * @return The index of the vertex, or -1 if the vertex does not exist.
     */
    private int findIndexByName(String name) {
        name = name.toLowerCase();
        int size = graph.size();
        for (int i = 0; i < size; i++) {
            Object label = graph.getLabel(i);
            if (label != null && label.toString().toLowerCase().equals(name)) { // A case insensitive check to see if the label is not null and matches the name
                return i;
            }
        }
        return -1;
    }

    /**
     * Prompts the user to enter an index.txt and friend.txt file to load the network.
     * 
     * @param scanner The scanner to read user input.
     */
    public void promptToLoadNetwork(Scanner scanner) {
        System.out.print("Enter the path for the index file: ");
        String indexFile = scanner.nextLine();
        System.out.print("Enter the path for the friend file: ");
        String friendFile = scanner.nextLine();
        loadNetwork(indexFile, friendFile);
    }

    ////// Task 2 - friends list //////

    /**
     * Prompts the user to enter a name and lists their friends.
     * 
     * @param scanner The scanner to read user input.
     */
    public void promptToFindFriends(Scanner scanner) {
        System.out.print("Enter a name to list their friends: ");
        String name = scanner.nextLine();
        listFriends(name);
    }

    /**
     * Lists the friends of a given person by their name.
     * 
     * @param name The name of the person whose friends are to be listed.
     */
    public void listFriends(String name) {
        if (graph == null || graph.size() == 0) {
            System.out.println("The network is empty.");
            return;
        }

        int index = findIndexByName(name);
        if (index == -1) {
            System.out.println("Name does not exist in the network.");
            return;
        }

        printFriends(index);
    }

    /**
     * Prints the friends of a vertex specified by its index.
     * 
     * @param index The index of the vertex.
     */
    private void printFriends(int index) {
        int[] neighbors = graph.neighbors(index);
        System.out.println(graph.getLabel(index) + " is friends with:");
        if (neighbors.length == 0) {
            System.out.println("This person has no friends listed.");
        } else {
            for (int neighbor : neighbors) {
                System.out.println(graph.getLabel(neighbor));
            }
        }
    }

    ////// Task 3 - friends and friends' of friends list  //////

    /**
     * Prompts the user to enter a name and lists their friends and their friends' friends.
     * 
     * @param scanner The scanner to read user input.
     */
    public void promptToFindFriendsOfFriends(Scanner scanner) {
        System.out.print("Enter a name to list their friends and friends' friends: ");
        String name = scanner.nextLine();
        listExtendedNetwork(name);
    }

    /**
     * Lists the friends and friends' friends of a given person by their name.
     * 
     * @param name The name of the person whose friends and friends' friends are to be listed.
     */
    public void listExtendedNetwork(String name) {
        if (graph == null || graph.size() == 0) {
            System.out.println("The network is empty.");
            return;
        }

        int index = findIndexByName(name);
        if (index == -1) {
            System.out.println("The name '" + name + "' does not exist in the network.");
            return;
        }

        Set<String> friendsNetwork = findExtendedFriends(index);
        if (friendsNetwork.isEmpty()) {
            System.out.println(name + " has no friends or friends of friends listed.");
        } else {
            System.out.println(name + " is friends with, or has friends who know:");
            friendsNetwork.forEach(System.out::println);
        }
    }

    /**
     * Finds the extended network (friends and friends' friends) of a vertex specified by its index.
     * 
     * @param index The index of the vertex.
     * @return A set of names representing the extended network.
     */
    private Set<String> findExtendedFriends(int index) {
        Set<String> results = new HashSet<>();
        addFriendsToSet(index, results);
        return results;
    }

    /**
     * Adds the friends of a vertex to a set of results.
     * 
     * @param index The index of the vertex.
     * @param results The set to add the friends to.
     */
    private void addFriendsToSet(int index, Set<String> results) {
        int[] neighbors = graph.neighbors(index);

        for (int neighbor : neighbors) {
            results.add(graph.getLabel(neighbor).toString());
            addFriendsOfFriendsToSet(neighbor, index, results);
        }
    }

    /**
     * Adds the friends of a friend's vertex to a set of results, excluding the original vertex.
     * 
     * @param friendIndex The index of the friend's vertex.
     * @param originalIndex The index of the original vertex.
     * @param results The set to add the friends of friends to.
     */
    private void addFriendsOfFriendsToSet(int friendIndex, int originalIndex, Set<String> results) {
        int[] friendsOfFriend = graph.neighbors(friendIndex);

        for (int friendOfFriend : friendsOfFriend) {
            if (friendOfFriend != originalIndex) {
                results.add(graph.getLabel(friendOfFriend).toString());
            }
        }
    }

    ////// Task 4 - common friends //////

    /**
     * Prompts the user to enter two names and lists their common friends.
     * 
     * @param scanner The scanner to read user input.
     */
    public void promptToFindCommonFriends(Scanner scanner) {
        System.out.print("Enter the first name to find common friends: ");
        String name1 = scanner.nextLine();
        System.out.print("Enter the second name to find common friends: ");
        String name2 = scanner.nextLine();
        showCommonFriends(name1, name2);
    }

    /**
     * Lists the common friends of two given people by their names.
     * 
     * @param name1 The name of the first person.
     * @param name2 The name of the second person.
     */
    public void showCommonFriends(String name1, String name2) {
        if (graph == null || graph.size() == 0) {
            System.out.println("The network is empty.");
            return;
        }

        Set<String> friendsName1 = getFriendsOf(name1);
        Set<String> friendsName2 = getFriendsOf(name2);

        if (friendsName1 == null || friendsName2 == null) {
            if (friendsName1 == null) {
                System.out.println("The name '" + name1 + "' does not exist in the network.");
            }
            if (friendsName2 == null) {
                System.out.println("The name '" + name2 + "' does not exist in the network.");
            }
            return;
        }

        friendsName1.retainAll(friendsName2); // Intersection of two sets

        if (friendsName1.isEmpty()) {
            System.out.println(name1 + " and " + name2 + " have no common friends.");
        } else {
            System.out.println(name1 + " and " + name2 + " both know:");
            friendsName1.forEach(System.out::println);
        }
    }

    /**
     * Gets the friends of a given person by their name.
     * 
     * @param name The name of the person whose friends are to be retrieved.
     * @return A set of names representing the friends of the person, or null if the person does not exist.
     */
    private Set<String> getFriendsOf(String name) {
        int index = findIndexByName(name);
        if (index == -1) {
            return null; // Name does not exist
        }

        Set<String> friends = new HashSet<>();
        int[] neighbors = graph.neighbors(index);

        for (int neighbor : neighbors) {
            friends.add(graph.getLabel(neighbor).toString());
        }
        return friends;
    }

    ////// Task 5 - delete a member //////

    /**
     * Prompts the user to enter a name and deletes the member from the network.
     * 
     * @param scanner The scanner to read user input.
     */
    public void promptToDeleteMember(Scanner scanner) {
        if (graph == null || graph.size() == 0) {
            System.out.println("The network is empty.");
            return;
        }

        System.out.print("Enter the name of the member to delete: ");
        String name = scanner.nextLine();

        if (!confirmDeletion(name, scanner)) {
            System.out.println("Deletion cancelled.");
            return;
        }

        deleteMember(name);
        System.out.println(name + " has been successfully deleted from the network.");
    }

    /**
     * Confirms the deletion of a member from the network.
     * 
     * @param name The name of the member to delete.
     * @param scanner The scanner to read user input.
     * @return True if the deletion is confirmed, false otherwise.
     */
    private boolean confirmDeletion(String name, Scanner scanner) {
        System.out.println("Are you sure you want to delete " + name + " and all their connections? (y/n)");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("y");
    }

    /**
     * Deletes a member and all their connections from the network.
     * 
     * @param name The name of the member to delete.
     */
    private void deleteMember(String name) {
        int index = findIndexByName(name);
        if (index == -1) {
            System.out.println("The name '" + name + "' does not exist in the network.");
            return;
        }

        // Removing all connections of this person
        int size = graph.size();
        for (int i = 0; i < size; i++) {
            if (graph.isEdge(index, i)) {
                graph.removeEdge(index, i);
            }
            if (graph.isEdge(i, index)) {
                graph.removeEdge(i, index);
            }
        }
        // Clear the name label
        graph.setLabel(index, null);
    }

    ////// Task 6 - print all members in social network //////

    /**
     * Prints all members in the social network, sorted by popularity and name.
     */
    public void printAllMembers() {
        if (graph == null || graph.size() == 0) {
            System.out.println("The network is empty.");
            return;
        }

        List<Member> members = getSortedMembers();

        System.out.println("Report Name: All Members Sorted by Popularity and Name:");
        System.out.println(String.format("%-20s %s", "Name", "Number of Friends"));
        members.forEach(member -> System.out.println(String.format("%-20s %d", member.getName(), member.getFriendCount()))); // Print the members name and the number of friends
    }

    /**
     * Gets a list of members sorted by the number of friends (popularity) and name.
     * 
     * @return A list of members sorted by popularity and name.
     */
    private List<Member> getSortedMembers() {
        List<Member> members = new ArrayList<>();
        int size = graph.size();

        for (int i = 0; i < size; i++) {
            Object label = graph.getLabel(i);
            if (label != null) {
                members.add(new Member(label.toString(), countFriends(label.toString()))); // Create a new object with the vertex label and the number of friends, and add it to the members list
            }
        }

        return members.stream() // Sort the members list by the number of friends (largest to smallest), then by name (smallest to largest)
                .sorted(Comparator.comparing(Member::getFriendCount).reversed()
                        .thenComparing(Member::getName))
                .collect(Collectors.toList()); // Collect the sorted members into a list and return it
    }

    /**
     * Counts the number of friends of a given person by their name.
     * 
     * @param name The name of the person whose friends are to be counted.
     * @return The number of friends the person has.
     */
    private int countFriends(String name) {
        int index = findIndexByName(name);
        int count = 0;
        int[] neighbors = graph.neighbors(index);
        for (int neighbor : neighbors) {
            if (graph.isEdge(index, neighbor)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Represents a member of the social network.
     */
    private static class Member {
        private String name;  // The name of the member
        private int friendCount;  // The number of friends the member has

        /**
         * Constructs a new Member with the specified name and friend count.
         * 
         * @param name The name of the member.
         * @param friendCount The number of friends the member has.
         */
        public Member(String name, int friendCount) {
            this.name = name;
            this.friendCount = friendCount;
        }

        /**
         * Returns the name of the member.
         * 
         * @return The name of the member.
         */
        public String getName() {
            return name;
        }

        /**
         * Returns the number of friends the member has.
         * 
         * @return The number of friends the member has.
         */
        public int getFriendCount() {
            return friendCount;
        }
    }
}
