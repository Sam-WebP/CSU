import java.io.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Social network as a graph where nodes are individuals and edges are the connections between them.
 * Methods are used to load the network data from files and to access the graph.
 */
public class SocialNetwork {
    private Graph graph;
    private String indexFilePath;  // Field to store the path of the index file
    private String friendFilePath;  // Field to store the path of the friend file

    public SocialNetwork() {
        this.graph = new Graph(0);  // Initialize with no vertices; adjusted as needed
    }

    /**
     * Load network data from the index and friend files.
     *
     * @param indexFile File path that contains the vertex data.
     * @param friendFile File path that contains the edge data.
     */
    public void loadNetwork(String indexFile, String friendFile) {
        this.indexFilePath = indexFile;  // Store the index file path
        this.friendFilePath = friendFile;  // Store the friend file path
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
        this.graph = new Graph(vertexCount);  // Reinitialize graph with the correct number of vertices

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
     * Friendship connections are read from the friend file and adds the edges to the graph.
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
     * Getter method for returning the graph representing the social network.
     *
     * @return The graph object populated with vertices and edges.
     */
    public Graph getGraph() {
        return graph;
    }

    // For testing: print adjacency matrix
    public void printAdjacencyMatrix() {
        boolean[][] matrix = getEdges();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print((matrix[i][j] ? 1 : 0) + " ");
            }
            System.out.println();
        }
    }
    
    private int findIndexByName(String name) {
        name = name.toLowerCase();
        Object[] labels = getLabels();
        for (int i = 0; i < labels.length; i++) {
            if (labels[i] != null && labels[i].toString().toLowerCase().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    ////// Task 2 - friends list //////

    public void promptToFindFriends(Scanner scanner) {
        System.out.print("Enter a name to list their friends: ");
        String name = scanner.nextLine();
        listFriends(name);
    }

    public void listFriends(String name) {
        if (graph == null || getLabels().length == 0) {
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

    private void printFriends(int index) {
        boolean[][] matrix = getEdges();
        Object[] labels = getLabels();
        boolean foundFriends = false;
        System.out.println(labels[index] + " is friends with:");
        for (int j = 0; j < matrix.length; j++) {
            if (matrix[index][j]) {
                System.out.println(labels[j]);
                foundFriends = true;
            }
        }
        if (!foundFriends) {
            System.out.println("This person has no friends listed.");
        }
    }

    ////// Task 3 - friends and friends' of friends list  //////

    public void promptToFindFriendsOfFriends(Scanner scanner) {
        System.out.print("Enter a name to list their friends and friends' friends: ");
        String name = scanner.nextLine();
        listExtendedNetwork(name);
    }

    public void listExtendedNetwork(String name) {
        if (graph == null || getLabels().length == 0) {
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

    private Set<String> findExtendedFriends(int index) {
        Set<String> results = new HashSet<>();
        addFriendsToSet(index, results);
        return results;
    }

    private void addFriendsToSet(int index, Set<String> results) {
        boolean[][] matrix = getEdges();
        Object[] labels = getLabels();

        for (int j = 0; j < matrix[index].length; j++) {
            if (matrix[index][j]) {
                results.add(labels[j].toString());
                addFriendsOfFriendsToSet(j, index, results);
            }
        }
    }

    private void addFriendsOfFriendsToSet(int friendIndex, int originalIndex, Set<String> results) {
        boolean[][] matrix = getEdges();
        Object[] labels = getLabels();

        for (int k = 0; k < matrix[friendIndex].length; k++) {
            if (matrix[friendIndex][k] && k != originalIndex) {
                results.add(labels[k].toString());
            }
        }
    }

    ////// Task 4 - common friends //////

    public void promptToFindCommonFriends(Scanner scanner) {
        System.out.print("Enter the first name to find common friends: ");
        String name1 = scanner.nextLine();
        System.out.print("Enter the second name to find common friends: ");
        String name2 = scanner.nextLine();
        showCommonFriends(name1, name2);
    }
    
    public void showCommonFriends(String name1, String name2) {
        if (graph == null || getLabels().length == 0) {
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

    private Set<String> getFriendsOf(String name) {
        int index = findIndexByName(name);
        if (index == -1) {
            return null; // Name does not exist
        }

        Set<String> friends = new HashSet<>();
        boolean[][] matrix = getEdges();
        Object[] labels = getLabels();

        for (int j = 0; j < matrix[index].length; j++) {
            if (matrix[index][j]) {
                friends.add(labels[j].toString());
            }
        }
        return friends;
    }

    ////// Task 5 - delete a member //////                                                        
                                                             
    public void promptToDeleteMember(Scanner scanner) {
        if (graph == null || getLabels().length == 0) {
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

    private boolean confirmDeletion(String name, Scanner scanner) {
        System.out.println("Are you sure you want to delete " + name + " and all their connections? (y/n)");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("y");
    }

    private void deleteMember(String name) {
        int index = findIndexByName(name);
        if (index == -1) {
            System.out.println("The name '" + name + "' does not exist in the network.");
            return;
        }

        // Removing all connections of this person
        boolean[][] matrix = getEdges();
        for (int i = 0; i < getLabels().length; i++) {
            matrix[index][i] = false;
            matrix[i][index] = false;
        }
        // Clear the name label
        getLabels()[index] = null;
    }

    // Task 6 - print all members in social network

    public void printAllMembers() {
        if (graph == null || getLabels() == null || getLabels().length == 0) {
            System.out.println("The network is empty.");
            return;
        }

        Object[] labels = getLabels();

        List<Member> members = getSortedMembers();

        System.out.println("Report Name: All Members Sorted by Popularity and Name:");
        System.out.println(String.format("%-20s %s", "Name", "Number of Friends"));
        members.forEach(member -> System.out.println(String.format("%-20s %d", member.getName(), member.getFriendCount())));
    }

    private List<Member> getSortedMembers() {
        return Arrays.stream(getLabels())
                .filter(Objects::nonNull)  // Filters out any null labels.
                .map(label -> new Member(label.toString(), countFriends(label.toString())))  // Creates a new Member object for each label.
                .sorted(Comparator.comparing(Member::getFriendCount).reversed()  // First sort by friend count, descending.
                        .thenComparing(Member::getName))  // Then sort by name if friend counts are equal.
                .collect(Collectors.toList());  // Collect results into a list.
    }

    private int countFriends(String name) {
        int index = findIndexByName(name);
        int count = 0;
        boolean[][] matrix = getEdges();
        for (int i = 0; i < getLabels().length; i++) {
            if (matrix[index][i]) {
                count++;
            }
        }
        return count;
    }

    private class Member {
        private String name;
        private int friendCount;

        public Member(String name, int friendCount) {
            this.name = name;
            this.friendCount = friendCount;
        }

        public String getName() {
            return name;
        }

        public int getFriendCount() {
            return friendCount;
        }
    }

    // Reflection-based methods to access private fields of Graph
    private boolean[][] getEdges() {
        try {
            Field field = Graph.class.getDeclaredField("edges");
            field.setAccessible(true);
            return (boolean[][]) field.get(graph);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Object[] getLabels() {
        try {
            Field field = Graph.class.getDeclaredField("labels");
            field.setAccessible(true);
            return (Object[]) field.get(graph);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
