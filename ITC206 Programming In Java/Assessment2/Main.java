package Assessment2;

public class Main {
    
    public static void main(String[] args) {

        int[] testMarks = Marks.getMarks();

        int max = ProcessMarks.max(testMarks);
        int min = ProcessMarks.min(testMarks);
        int range = ProcessMarks.range(testMarks);
        double mean = ProcessMarks.mean(testMarks);
        double median = ProcessMarks.median(testMarks);
        int mode = ProcessMarks.mode(testMarks);
        String grades = ProcessMarks.gradez(testMarks);

        for (int n = 0; n < testMarks.length; n++) {
			System.out.print(testMarks[n] + " ");
			if (n % 10 == 9)
				System.out.println();
		}

        System.out.println();
        System.out.println();

        System.out.println("Max = " + max);
        System.out.println("Min = " + min);
        System.out.println("Range = " + range);
        System.out.println("Mean = " + mean);
        System.out.println("Median = " + median);
        System.out.println("Mode = " + mode);
        System.out.println("Grades = " + grades);

    }

}
