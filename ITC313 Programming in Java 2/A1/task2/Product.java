package task2;

/**
 * Product class which has a private name, price, and quantity.
 * Implements Comparable for sorting by name and Cloneable for creating copies.
 */
public class Product implements Comparable<Product>, Cloneable {
    private String name;
    private double price;
    private int quantity;

    /**
     * Constructs a new Product.
     *
     * @param name     name of the product
     * @param price    price of the product
     * @param quantity quantity of the product
     */
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    /**
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * @return the price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return the quantity of the product
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Compares a product's name with another product's names.
     *
     * @param other the product to be compared
     * @return a negative int, zero, or a positive int as this product's name
     *         is less than, equal to, or greater than the specified product's name
     */
    @Override
    public int compareTo(Product other) {
        return this.name.compareTo(other.name);
    }

    /**
     * @return Creates and returns a copy of this product.
     */
    @Override
    public Product clone() {
        try {
            return (Product) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Product(this.name, this.price, this.quantity);
        }
    }

    /**
     * @return a string version of the product
     */
    @Override
    public String toString() {
        return name + " - $" + String.format("%.1f", price) + " - " + quantity;
    }
}
