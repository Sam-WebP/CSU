package task1;
public class Rotation {

    public static int[] leftRotation(int[] arr, int d) {
        int n = arr.length;
        
        d = d % n; // Avoid unnecessary rotations
        
        reverse(arr, 0, d - 1); // Reverse first d elements
        reverse(arr, d, n - 1); // Reverse remaining n - d elements     
        reverse(arr, 0, n - 1); // Reverse entire array

        return arr;
    }

    private static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
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