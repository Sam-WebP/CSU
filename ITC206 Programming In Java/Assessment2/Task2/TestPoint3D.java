package Assessment2.Task2;

public class TestPoint3D {
    public static void main(String[] args) {

        // Construct Point3D classes:
        Point3D p1 = new Point3D(4, 6, 1, "blue");
        Point3D p2 = new Point3D(10, 56, 32, "yellow");
        Point3D p3 = new Point3D(32, 5, 78, "red");
        Point3D p4 = new Point3D(174, 45.5, 65, "green");
        Point3D p5 = new Point3D(); // Use the alternative Point3D overloaded constructor

        // Print the values before setting new values for p5 (testing getters):
        System.out.println("p5 X before setter: " + p5.getX());
        System.out.println("p5 Y before setter: " + p5.getY());
        System.out.println("p5 Z before setter: " + p5.getZ());
        System.out.println("p5 colour before setter: " + p5.getColour());

        // Use the setter methods to change the values for p5 (testing setters):
        p5.setX(3.5);
        p5.setY(15);
        p5.setZ(20.6);
        p5.setColour("black");

        // Print the updated values of p5 (testing getters & setters):
        System.out.println("p5 X after setter: " + p5.getX());
        System.out.println("p5 Y after setter: " + p5.getY());
        System.out.println("p5 Z after setter: " + p5.getZ());
        System.out.println("p5 colour after setter: " + p5.getColour());

        // Initiate an array of Point3D objects:
        Point3D[] points = {p1, p2, p3, p4, p5};

        // Run the max and min methods on an array of Point3D objects to find the min and max distance and coordinate combinations:
        max(points);
        System.out.println();
        min(points);

        // Print the returned answers from the two distance methods (testing the distance method)
        System.out.println("The distance between p1 & p2 is " + p1.distance(p2)); // Distance method (Point3D class argument version)
        System.out.println("The distance between p1 & the coordinates 1.5, 5 and 50 is " + p1.distance(1.5, 5, 50)); // Distance method (Coordinate argument version)
    }

    
    public static void max(Point3D[] points) {
        double maxDistance = 0;
        Point3D[] maxDistancePair = new Point3D[2];
        
        for (int i = 0; i < points.length; i++) { 
            for (int j = i+1; j < points.length; j++) { // Starting the second loop at i+1 to avoid iterating and calculating the same coordinates more than once
                double currentDistance = points[i].distance(points[j]);

                if (currentDistance > maxDistance) {
                    maxDistance = currentDistance;
                    maxDistancePair[0] = points[i];
                    maxDistancePair[1] = points[j];
                }
            }
        }
        System.out.println("The maximum distance between any two points is: " + maxDistance);
        System.out.println("This distance occurs between points: " + maxDistancePair[0].getX() + ", " + maxDistancePair[0].getY() + ", " + maxDistancePair[0].getZ() + " & " + maxDistancePair[1].getX() + ", " + maxDistancePair[1].getY() + ", " + maxDistancePair[1].getZ());
    }

    public static void min(Point3D[] points) {
        double minDistance = points[0].distance(points[1]);
        Point3D[] minDistancePair = new Point3D[2];
        
        for (int i = 0; i < points.length; i++) { 
            for (int j = i+1; j < points.length; j++) { // Starting the second loop at i+1 to avoid iterating and calculating the same coordinates more than once
                double currentDistance = points[i].distance(points[j]);

                if (currentDistance < minDistance) {
                    minDistance = currentDistance;
                    minDistancePair[0] = points[i];
                    minDistancePair[1] = points[j];
                }
            }
        }
        System.out.println("The minimum distance between any two points is: " + minDistance);
        System.out.println("This distance occurs between points: " + minDistancePair[0].getX() + ", " + minDistancePair[0].getY() + ", " + minDistancePair[0].getZ() + " & " + minDistancePair[1].getX() + ", " + minDistancePair[1].getY() + ", " + minDistancePair[1].getZ());
    }


}
