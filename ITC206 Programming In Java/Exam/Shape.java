package Exam;

public abstract class Shape {
    
    private String colour;

    // Constructor that only takes colour
    public Shape(String colour) {
        this.colour = colour;
    }

    // Getter for colour
    public String getColour() {
        return colour;
    }

    // Setter for colour
    public void setColour(String colour) {
        this.colour = colour;
    }

    // Abstract method to get area 
    public double getArea() {
        return 0;
    }


}
