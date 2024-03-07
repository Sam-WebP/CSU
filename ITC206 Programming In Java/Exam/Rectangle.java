package Exam;

public class Rectangle extends Shape {
    //Contructor for length, width and colour
    private double length;
    private double width;

    public Rectangle(double length, double width, String colour) {
        super(colour);
        this.length = length;
        this.width = width;
    }

    public double getArea() {
        return length * width;
    }
}
