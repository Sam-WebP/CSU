package task1;
public class Rotation {

    /**
     * Performs a left rotation on an array with the specified d number of positions 
     * by using a reverse operation to achieve the desired outcome
     * with optimal time and space complexity.
     *
     * @param arr The array that will be rotated.
     * @param d The number of positions to rotate the array to the left.
     * @return The rotated array.
     */
    public static int[] leftRotation(int[] arr, int d) {
        int n = arr.length;
        
        d = d % n; // Avoid unnecessary rotations
        
        reverse(arr, 0, d - 1); // Reverse first d elements
        reverse(arr, d, n - 1); // Reverse remaining n - d elements     
        reverse(arr, 0, n - 1); // Reverse entire array

        return arr;
    }

    /**
     * Reverses a portion of the array from the 'start' to the 'end' indexes.
     *
     * @param arr The array where the elements are going to be reversed.
     * @param start The starting index of the portion are going to be reversed.
     * @param end The ending index of the portion to be reversed.
     */
    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            // Swap the elements at the start and end
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            // Move towards the center to be reversed
            start++;
            end--;
        }
    }

}

// --------------------PLEASE NOTE-------------------- // 
// I originally had the rotation class like below, however,
// I found that the above version that I ended up using 
// has better spacial complexity. 
//
// UPDATED VERSION:
// Time Complexity = O(n)
// Space Complexity = O(1) (because it doesn't use any additional data structures)
//
// ORIGINAL VERSION:
// Time Complexity = O(n)
// Space Complexity = O(n) (because it creates a new array of the same size)
// 
// Original, less optimised version is below:
//
// public static int[] leftRotation(int[] arr, int d) {
//     int n = arr.length;
//     d = d % n;
//     int[] rotated = new int[n];
//     for (int i = 0; i < n; i++) {
//         int newLoc = (i + n - d) % n;
//         rotated[newLoc] = arr[i];
//     }
//     return rotated;
// }