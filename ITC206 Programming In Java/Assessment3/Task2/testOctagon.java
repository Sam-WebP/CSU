package Assessment3.Task2;

public class testOctagon {
    
    public static void main(String[] args) {

        // Create the original octagon object
        Octagon octagonOriginal = new Octagon(5);

        // Clone the octagon object
        Octagon octagonDuplicate = octagonOriginal.clone();

        // Change the cloned version's length to 10 from 5
        octagonDuplicate.setSideLength(10);

        // Change the original octagon's colour to green from white
        octagonOriginal.setColour("Green");

        System.out.println();

        // Use the toString method to check both the original and the duplicated specifications
        System.out.println("Original Octagon:");
        System.out.println(octagonOriginal);
        System.out.println("Duplicate Octagon:");
        System.out.println(octagonDuplicate);

        // Get the result of comparing the original with the duplicate
        int compareResult = octagonOriginal.compareTo(octagonDuplicate);
    
        // Based on the result, return the relevant information back to the user
        if (compareResult == 1) {
            System.out.println("octagonOriginal has the greater area.");
        } else if (compareResult == -1) {
            System.out.println("octagonOriginal has a smaller area.");
        } else {
            System.out.println("Both octagons have the same area.");
        }

    }

}
