package Assessment2.Task2;

public class Point3D {
    
    double x;
    double y;
    double z;
    String colour;

    Point3D() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.colour = "Red";
    }

    Point3D(double x, double y, double z, String colour) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.colour = colour;
    }

    // Getters: 
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public String getColour() {
        return colour;
    }
   
    // Setters: 
    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public double distance(Point3D secondPoint) {
        return Math.sqrt(Math.pow(this.x - secondPoint.x, 2) + Math.pow(this.y - secondPoint.y, 2) + Math.pow(this.z - secondPoint.z, 2));
    }

    public double distance(double x, double y, double z) {
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2) + Math.pow(this.z - z, 2));
    }

}
