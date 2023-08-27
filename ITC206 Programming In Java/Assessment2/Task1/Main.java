package Assessment2.Task1;

public class Main {
    public static void main(String[] args) {

        int[] testMarks = Marks.getMarks(); // Get the marks array using the Marks class

        // Loop to print the marks array:
        for (int n = 0; n < testMarks.length; n++) {
			System.out.print(testMarks[n] + " ");
			if (n % 30 == 29) // Modified to meet the requirement "The grades should be displayed 30 per line"
				System.out.println();
		}

        // Initiate variables, assigning relevant values from the ProcessMarks class methods:
        int max = ProcessMarks.max(testMarks);
        int min = ProcessMarks.min(testMarks);
        int range = ProcessMarks.range(testMarks);
        double mean = ProcessMarks.mean(testMarks);
        double median = ProcessMarks.median(testMarks); 
        int mode = ProcessMarks.mode(testMarks); 
        char[] grades = ProcessMarks.grades(testMarks);
        int[] gradeDistn = ProcessMarks.gradeDistn(grades);

        System.out.println();
        System.out.println();

        // Display the variable values to the user:
        System.out.println("Max = " + max);
        System.out.println("Min = " + min);
        System.out.println("Range = " + range);
        System.out.println("Mean = " + mean);
        System.out.println("Median = " + median);
        System.out.println("Mode = " + mode);

        System.out.println();

        // Display the distribution of grades
        System.out.println("A: " + gradeDistn[0]);
        System.out.println("B: " + gradeDistn[1]);
        System.out.println("C: " + gradeDistn[2]);
        System.out.println("D: " + gradeDistn[3]);
        System.out.println("E: " + gradeDistn[4]);
        System.out.println("F: " + gradeDistn[5]);


    }

}
