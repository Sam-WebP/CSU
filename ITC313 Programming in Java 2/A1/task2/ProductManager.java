package task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Manages a collection of Product objects by allowing products
 * to be added, displayed in sorted lists, find the highest-priced product,
 * and clone the entire collection.
 */
public class ProductManager {
    private ArrayList<Product> products = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    /**
     * User inputs product info to be added.
     */
    public void addProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter product quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        products.add(new Product(name, price, quantity));
        System.out.println("Product added successfully.");
    }

    /**
     * Displays the products sorted by name (ascending).
     */
    public void displaySortedByName() {
        Collections.sort(products);
        System.out.println("Products sorted by name (ascending):");
        for (Product p : products) {
            System.out.println(p.getName());
        }
    }

    /**
     * Displays the products sorted by price (descending).
     */
    public void displaySortedByPrice() {
        products.sort(Comparator.comparingDouble(Product::getPrice).reversed());
        System.out.println("Products sorted by price (descending):");
        for (Product p : products) {
            System.out.println(p.getName() + " - $" + String.format("%.1f", p.getPrice()));
        }
    }

    /**
     * Displays the products sorted by quantity (ascending).
     */
    public void displaySortedByQuantity() {
        products.sort(Comparator.comparingInt(Product::getQuantity));
        System.out.println("Products sorted by quantity (ascending):");
        for (Product p : products) {
            System.out.println(p.getName() + " - " + p.getQuantity());
        }
    }

    /**
     * Displays the highest priced product.
     */
    public void displayHighestPrice() {
        Product highestPriced = Collections.max(products, Comparator.comparingDouble(Product::getPrice));
        System.out.println("Product with the highest price: " + highestPriced.getName());
    }

    /**
     * Clones the whole collection of products into a
     * new ArrayList and displays the contents of the cloned list
     */
    public void displayClonedProducts() {
        ArrayList<Product> clonedProducts = new ArrayList<>();
        for (Product p : products) {
            clonedProducts.add(p.clone());
        }
        System.out.println("Cloned products:");
        for (Product p : clonedProducts) {
            System.out.println(p.getName());
        }
    }

    /**
     * Runs the main program loop,
     * allowing the user to interact with the program.
     */
    public void run() {
        while (true) {
            System.out.println("\n1. Add Product");
            System.out.println("2. Display Products Sorted by Name");
            System.out.println("3. Display Products Sorted by Price");
            System.out.println("4. Display Products Sorted by Quantity");
            System.out.println("5. Display Highest Priced Product");
            System.out.println("6. Display Cloned Products");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    displaySortedByName();
                    break;
                case 3:
                    displaySortedByPrice();
                    break;
                case 4:
                    displaySortedByQuantity();
                    break;
                case 5:
                    displayHighestPrice();
                    break;
                case 6:
                    displayClonedProducts();
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * Entry point for the application.
     */
    public static void main(String[] args) {
        new ProductManager().run();
    }
}