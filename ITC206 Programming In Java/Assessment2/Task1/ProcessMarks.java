package Assessment2.Task1;
import java.util.Arrays;

public class ProcessMarks {
    
    // Find the maximum mark in the array
    public static int max(int[] marks) {
        int highest = marks[0];

        for (int i = 0; i < marks.length; i++) {
            if (highest < marks[i]) {
                highest = marks[i];
            }
        }

        return highest;
    }

    // Find the minimum mark in the array
    public static int min(int[] marks) {
        int lowest = marks[0];

        for (int i = 0; i < marks.length; i++) {
            if (lowest > marks[i]) {
                lowest = marks[i];
            }
        }

        return lowest; 
    }

    // Find the range of marks in the array
    public static int range(int[] marks) {
        int lowest = marks[0];
        int highest = marks[0];

        for (int i = 0; i < marks.length; i++) {
            if (lowest > marks[i]) {
                lowest = marks[i];
            }
            if (highest < marks[i]) {
                highest = marks[i];
            }
        }

        return highest - lowest;
    }

    // Find the mean of the marks in the array
    public static double mean(int[] marks) {
        int total = 0;

        for (int i = 0; i < marks.length; i++) {
            total += marks[i];
        }

        return (double) total / marks.length; // Return the mean as a double because java converts int division to an int by default
    }

    // Find the median of the marks in the array
    public static double median(int[] marks) {
        int evenMid1 = 0;
        int evenMid2 = 0;

        int[] sortedMarks = Arrays.copyOf(marks, marks.length);
        Arrays.sort(sortedMarks); // Sort the array in ascending order

        if (sortedMarks.length % 2 == 0) { // Length is even
            evenMid1 = sortedMarks.length / 2 - 1;
            evenMid2 = sortedMarks.length / 2;

            return (double) (sortedMarks[evenMid1] + sortedMarks[evenMid2]) / 2; // Return average of both middle points
        } else { // Length is odd
            return sortedMarks[(sortedMarks.length) / 2]; // Return the middle point
        }
    }

    // Find the mode of the marks in the array
    public static int mode(int[] marks) {
        int[] sortedMarks = Arrays.copyOf(marks, marks.length);
        Arrays.sort(sortedMarks); // Sort the array in ascending order

        int previousNum = sortedMarks[0];
        int currentStreakCount = 1;
        int highestStreakCount = 0;
        int highestStreakValue = sortedMarks[0];

        for (int i = 1; i < sortedMarks.length; i++) {
            if (sortedMarks[i] == previousNum) {
                currentStreakCount++;
            } else {
                currentStreakCount = 1;
            }

            if (currentStreakCount > highestStreakCount) {
                highestStreakCount = currentStreakCount;
                highestStreakValue = sortedMarks[i];
            }

            previousNum = sortedMarks[i];
        }
        return highestStreakValue;
    }

    // Assign grades based on the marks in the array
    public static char[] grades(int[] marks) {
        int[] gradeBoundaries = {90, 75, 60, 50, 45};
        char[] grades = {'A', 'B', 'C', 'D', 'E', 'F'};
        char[] gradeMatch = new char[marks.length];
    
        for (int i = 0; i < marks.length; i++) {
            for (int z = 0; z < gradeBoundaries.length; z++) {
                if (marks[i] >= gradeBoundaries[z]) {
                    gradeMatch[i] = grades[z];
                    break; // break out of the inner loop once a grade is assigned
                }
            }
            if (marks[i] < gradeBoundaries[4]) {
                gradeMatch[i] = 'F';
            }
        }
        return gradeMatch;
    }

    // Find the distribution of grades in the array
    public static int[] gradeDistn(char[] grades) {
        int[] gradeDistn = new int[grades.length];

        for (int i = 0; i < grades.length; i++) {
            if (grades[i] == 'A') {
                gradeDistn[0]++;
            } else if (grades[i] == 'B') {
                gradeDistn[1]++;
            } else if (grades[i] == 'C') {
                gradeDistn[2]++;
            } else if (grades[i] == 'D') {
                gradeDistn[3]++;
            } else if (grades[i] == 'E') {
                gradeDistn[4]++;
            } else {
                gradeDistn[5]++;
            }
        }
        
        return gradeDistn;
    }   

}
