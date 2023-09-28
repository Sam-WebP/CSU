package Assessment3.Task2;

// The Octagon class is a subclass off the GeometricObject class and implements the comparable and cloneable interfaces
public class Octagon extends GeometricObject implements Comparable<Octagon>, Cloneable {
    
    // To hold the length of the sides
    private double sideLength;

    // Default constructor that uses the default superclass constructor
    public Octagon() {
        super();
    }

    // Constructor to set a specific side length
    public Octagon(double sideLength) {
        super();
        this.sideLength = sideLength;
    }

    // Constructor to set all of the specifications of the octagon 
    public Octagon(String colour, boolean filled, double sideLength) {
        super(colour, filled);
        this.sideLength = sideLength;
    }

    // Getter for the side length of the octagon
    public double getSideLength() {
        return sideLength;
    }

    // Setter for the side length of the octagon
    public void setSideLength(double sideLength) { 
        this.sideLength = sideLength;
    }

    // Getter that calculates the area of the octagon based on the formula and side length
    public double getArea() {
        return (2 + 4 / Math.sqrt(2)) * sideLength * sideLength;
    }

    // Getter for the perimeter of the octagon
    public double getPerimeter() {
        return 8 * sideLength;
    }

    // From the comparable interface, implement the compareTo method to compare the areas of two octagons
    @Override
    public int compareTo(Octagon otherOctagon) {
        if (this.getArea() > otherOctagon.getArea()) return 1;
        if (this.getArea() < otherOctagon.getArea()) return -1;
        return 0;
    }

    // From the Implement the clone method to create a duplicate of an octagon 
    @Override
    public Octagon clone() {
        try {
            return (Octagon) super.clone();
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }

    // Return the octagon specifications as a string
    @Override
    public String toString() {
        return "Side length: " + sideLength + "\nColour: " + getColour() +"\nArea: " + getArea() + "\nPerimeter: " + getPerimeter() + "\n";
    }

}
