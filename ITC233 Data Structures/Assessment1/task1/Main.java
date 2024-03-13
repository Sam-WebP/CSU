package task1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        File inputFile = new File("input.txt");

        try (Scanner scanner = new Scanner(inputFile)) { 

            if (!scanner.hasNext()) {
                throw new IllegalArgumentException("The input file is empty.");
            }

            int n = scanner.nextInt();
            int d = scanner.nextInt();
            
            if (n <= 0 || d < 0 || d > n) {
                throw new IllegalArgumentException("Invalid values of n and/or d.");
            }

            scanner.nextLine();

            if (!scanner.hasNextLine()) {
                throw new IllegalArgumentException("The input file is missing required integers.");
            }
            
            String[] elements = scanner.nextLine().split("\\s+");
            if (elements.length != n) {
                throw new IllegalArgumentException("The number of integers provided does not match n.");
            }
            
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(elements[i]);
            }
            
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
