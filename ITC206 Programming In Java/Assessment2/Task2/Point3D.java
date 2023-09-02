package Assessment2.Task2;

public class Point3D {

    // Class attributes for the 3D coordinates and color of the point:
    double x;
    double y;
    double z;
    String colour;

    // Default constructor: initialises the point to (0,0,0) with a default color of "Red":
    Point3D() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.colour = "Red";
    }

    // Constructor that allows the user to input their own parameters:
    Point3D(double x, double y, double z, String colour) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.colour = colour;
    }

    // Retrieve the x-coordinate of the point:
    public double getX() {
        return x;
    }

    // Retrieve the y-coordinate of the point:
    public double getY() {
        return y;
    }

    // Retrieve the z-coordinate of the point:
    public double getZ() {
        return z;
    }

    // Retrieve the colour of the point:
    public String getColour() {
        return colour;
    }
   
    // Set the x-coordinate of the point:
    public void setX(double x) {
        this.x = x;
    }

    // Set the y-coordinate of the point:
    public void setY(double y) {
        this.y = y;
    }

    // Set the z-coordinate of the point:
    public void setZ(double z) {
        this.z = z;
    }

    // Set the colour of the point:
    public void setColour(String colour) {
        this.colour = colour;
    }
    
    // Calculate the distance between the current point and another Point3D object:
    public double distance(Point3D secondPoint) {
        return Math.sqrt(Math.pow(this.x - secondPoint.x, 2) + Math.pow(this.y - secondPoint.y, 2) + Math.pow(this.z - secondPoint.z, 2));
    }

    // Overloaded version to calculate the distance between the current point and another set of x, y, z coordinates:
    public double distance(double x, double y, double z) {
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2) + Math.pow(this.z - z, 2));
    }

}
