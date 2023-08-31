package Assessment2.Task2;

public class TestPoint3D {

    public static void main(String[] args) {

        // Initiate p1 and p2 using the two different Point3D constructors
        Point3D p1 = new Point3D(4, 6, 1, "blue");
        Point3D p2 = new Point3D(10, 56, 32, "yellow");
        Point3D p3 = new Point3D(32, 5, 78, "red");
        Point3D p4 = new Point3D(174, 45.5, 65, "green");
        Point3D p5 = new Point3D();

        // Values before setting new values for p5:
        System.out.println("p5 X before setter: " + p5.getX());
        System.out.println("p5 Y before setter: " + p5.getY());
        System.out.println("p5 Z before setter: " + p5.getZ());
        System.out.println("p5 colour before setter: " + p5.getColour());

        // Use the setter methods to change the values for p5
        p5.setX(3.5);
        p5.setY(15);
        p5.setZ(20.6);
        p5.setColour("black");

        // Values after setting new values for p5:
        System.out.println("p5 X after setter: " + p5.getX());
        System.out.println("p5 Y after setter: " + p5.getY());
        System.out.println("p5 Z after setter: " + p5.getZ());
        System.out.println("p5 colour after setter: " + p5.getColour());

        // Initiate an array of Point3D objects
        Point3D[] points = {p1, p2, p3, p4, p5};

        // Run the max and min methods on an array of Point3D objects to find the min and max distance coordinate combinations
        max(points);
        min(points);

        // Print the returned answers from the two distance methods
        System.out.println("The distance between p1 & p2 is " + p1.distance(p2));
        System.out.println("The distance between p1 & the coordinates 1.5, 5 and 50 is " + p1.distance(1.5, 5, 50));
    }

    public static double[][] objectArrToPoints(Point3D[] points) {
        
        double justPoints[][] = new double[points.length][];
        
        // Iterate through the points array and update each point within each object in that array into the currentPoints array and then send that array into the justPoints array. 
        for (int i = 0; i < points.length; i++) {
            double currentPoints[] = new double[3];
            currentPoints[0] = points[i].getX();
            currentPoints[1] = points[i].getY();
            currentPoints[2] = points[i].getZ();
            justPoints[i] = currentPoints;
        }

        // Print all the points with all the arrays inside the justPoints array
        // for (int i = 0; i < justPoints.length; i++) {
        //     System.out.println(justPoints[i][0]);
        //     System.out.println(justPoints[i][1]);
        //     System.out.println(justPoints[i][2]);
        // }

        return justPoints;
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
        
        double minDistance = 0;
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
