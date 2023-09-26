package Assessment3.Task2;

public class Octagon extends GeometricObject implements Comparable<Octagon>, Cloneable {
    
    private double sideLength;

    public Octagon() {
        super();
    }

    public Octagon(double sideLength) {
        super();
        this.sideLength = sideLength;
    }

    public Octagon(String colour, boolean filled, double sideLength) {
        super(colour, filled);
        this.sideLength = sideLength;
    }

    public double getSideLength() {
        return sideLength;
    }

    public void setSideLength(double sideLength) { 
        this.sideLength = sideLength;
    }

    public double getArea() {
        return (2 + 4 / Math.sqrt(2)) * sideLength * sideLength;
    }

    public double getPerimeter() {
        return 8 * sideLength;
    }

    @Override
    public int compareTo(Octagon otherOctagon) {
        if (this.getArea() > otherOctagon.getArea()) return 1;
        if (this.getArea() < otherOctagon.getArea()) return -1;
        return 0;
    }

    @Override
    public Octagon clone() {
        try {
            return (Octagon) super.clone();
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "Side length: " + sideLength + "\nColour: " + getColour() +"\nArea: " + getArea() + "\nPerimeter: " + getPerimeter() + "\n";
    }

}
