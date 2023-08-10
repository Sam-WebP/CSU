package Topic7Objectsandclasses.TestClasses;

public class Circle {

    /** The radius of this circle */
    public double radius = 1; /** Construct a circle object */

    public Circle() {} /** Construct a circle object */

    public Circle(double newRadius) {
        radius = newRadius;
    } /** Return the area of this circle */
    public double getArea() {
        return radius * radius * Math.PI;
      } /** Return the perimeter of this circle */
    public double getPerimeter() {
        return 2 * radius * Math.PI;
    }
    /** Set a new radius for this circle */
    public void setRadius(double newRadius) {
    radius = newRadius;
    }

} 



