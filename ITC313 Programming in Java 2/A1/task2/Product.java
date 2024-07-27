package A1.task2;

public class Product implements Comparable<Product>, Cloneable {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int compareTo(Product other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public Product clone() {
        try {
            return (Product) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Product(this.name, this.price, this.quantity);
        }
    }

    @Override
    public String toString() {
        return name + " - $" + String.format("%.1f", price) + " - " + quantity;
    }
}
