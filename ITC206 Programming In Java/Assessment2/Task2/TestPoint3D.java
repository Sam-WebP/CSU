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

        double[][] pointCoordinates = objectArrToPoints(points);

        max(points);

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

    // Takes in an array of arrays that has points so that it can calculate and display the max distance between the points in the array, and the pair of points where the max occurs
    public static void max(double[][] pointCoordinates) {
        
        double longestDistance = 0;
        
        for (int i = 0; i < pointCoordinates.length; i++) {
            Point3D currentPoint = new Point3D(pointCoordinates[i][0], pointCoordinates[i][1], pointCoordinates[i][2], "red");

            for (int z = 0; z < pointCoordinates.length; z++) {
                Point3D comparePoint = new Point3D(pointCoordinates[z][0], pointCoordinates[z][1], pointCoordinates[z][2], "red");

                if (currentPoint.distance(comparePoint) > longestDistance) {
                    longestDistance = currentPoint.distance(comparePoint);
                }
            }
            

        }

        

    }

    public static void max(Point3D[] points) {
        
        double longestDistance = 0;
        Point3D[] longestDistanceCombo = new Point3D[2];
        
        for (int i = 0; i < points.length; i++) {
            System.out.println("points[i] = " + points[i]);

            for (int z = 0; z < points.length; z++) {
                System.out.println("points[z] = " + points[z]);

                if (points[i].distance(points[z]) > longestDistance) {
                    longestDistance = points[i].distance(points[z]);
                    longestDistanceCombo[0] = points[i];
                    longestDistanceCombo[1] = points[z];
                }
            }
            

        }
        System.out.println("BELOW IS THE TEST OF THE RESPONSE:");
        System.out.println(longestDistanceCombo[0].getX());
        System.out.println(longestDistanceCombo[0].getY());
        System.out.println(longestDistanceCombo[0].getZ());
        System.out.println(longestDistanceCombo[1].getX());
        System.out.println(longestDistanceCombo[1].getY());
        System.out.println(longestDistanceCombo[1].getZ());


        // System.out.println("The longest distance is " + longestDistance);
        // System.out.println("The coordinates for them are " + longestDistanceCombo[0].getX + ", " + longestDistanceCombo[0].getY + ", " +  longestDistanceCombo[0].getZ );

    }

    
}
