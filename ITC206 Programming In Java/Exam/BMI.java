package Exam;

import java.util.Scanner;

public class BMI {
    
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("What is your weight (kgs)?");
        double weight = scan.nextDouble();
        
        System.out.println("What is your height (m)?");
        double height = scan.nextDouble();

        System.out.println("Your BMI is " + checkBMI(weight, height));        

        System.out.println();
        
    }

    public static String checkBMI(double weight, double height) {
        double bmi = weight / (height * height);

        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

}

