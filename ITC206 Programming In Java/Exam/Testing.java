package Exam;
import java.util.Scanner;

public class Testing {
        
    // public static void main(String[] args) {
    // Scanner scan = new Scanner(System.in);
    // int n = scan.nextInt();
    // double s = scan.nextDouble();
    // System.out.println("The area is " + area(n, s));
    // }

    // public static double area(int n, double s) {
    // return (n * Math.pow(s, 2)) / (4 * Math.tan(Math.PI / n));
    // }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int iOne = scan.nextInt();
        int iTwo = scan.nextInt();
        int iThree = scan.nextInt();
        int iFour = scan.nextInt();
        int iFive = scan.nextInt();
        System.out.println(gcd(iOne, iTwo, iThree, iFour, iFive));
    }

    public static int gcd(int... numbers) {
        int answer = numbers[0];
        for (int num : numbers) {
            answer = gcdTwo(answer, num);
        }
        return answer;
    }

    public static int gcdTwo(int a, int b) {
        return b == 0 ? a : gcdTwo(b, a % b);
    }


}


