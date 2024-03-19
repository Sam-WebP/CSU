package task1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The Main class represents the entry point of the program.
 * It reads input from a file, performs left rotation on an array,
 * and then prints the rotated array.
 */
public class Main {

    /**
     * Reads input from "input.txt", does the left rotation on the array, and prints the rotated array.
     * The first line of input gives the array size and the rotation count.
     * The second line is the array of elements.
     *
     * @param args Command line arguments (not used in this application).
     * @throws FileNotFoundException when the specified input file does not exist.
     * @throws IllegalArgumentException when the input values are invalid or the file format is incorrect.
     * @throws NoSuchElementException when the input file isn't structured properly.
     * @throws IllegalStateException when the scanner is closed prematurely.
     */
    public static void main(String[] args) {

        // Relative path to the input file from the project directory 
        File inputFile = new File("task1/input.txt");

        try (Scanner scanner = new Scanner(inputFile)) { 

            // Check if the file is empty
            if (!scanner.hasNext()) {
                throw new IllegalArgumentException("The input file is empty.");
            }

            // Get the size of the array from n and how many times to rotate from d
            int n = scanner.nextInt();
            int d = scanner.nextInt();
            
            // Validate n and d
            if (n <= 0 || d < 0 || d > n) {
                throw new IllegalArgumentException("Invalid values of n and/or d.");
            }

            scanner.nextLine();

            // Check if there are the required integers
            if (!scanner.hasNextLine()) {
                throw new IllegalArgumentException("The input file is missing required integers.");
            }

            // Check if format of the file matches the expected values for n
            String[] elements = scanner.nextLine().split("\\s+");
            if (elements.length != n) {
                throw new IllegalArgumentException("The number of integers provided does not match n.");
            }
            
            // Parse the elements into an integer array
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(elements[i]);
            }
            
            // Perform the left rotation using the Rotation class
            arr = Rotation.leftRotation(arr, d);
            
            for (int i = 0; i < n; i++) {
                System.out.print(arr[i] + (i < n - 1 ? " " : "\n"));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Could not find the file 'input.txt'.");
            e.printStackTrace();

        } catch (IllegalArgumentException | NoSuchElementException | IllegalStateException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

        }
    }
}
