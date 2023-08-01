package Task1;
import java.util.Scanner;

public class Population {
    
    // Constant that represents the number of seconds in a year
    final static int YEARLY_SECONDS = 31536000;
    public static void main(String[] args) {
        
        // Scanner to read user input
        Scanner input = new Scanner(System.in);

        // Prompt user for the number of seconds between each birth
        System.out.print("\nEnter the seconds for every birth: ");
        int birthInterval = input.nextInt();

        // Prompt user for the number of seconds between each death
        System.out.print("\nEnter the seconds for every death: ");
        int deathInterval = input.nextInt();

        // Prompt user for the number of seconds between each immigrant
        System.out.print("\nEnter the seconds for every new immigrant: ");
        int immigrantInterval = input.nextInt();

        // Prompt user for the number years to do the calculation across
        System.out.print("\nEnter the number of years: ");
        int years = input.nextInt();

        // Prompt user for the current population
        System.out.print("\nEnter the current population: ");
        int population = input.nextInt();

        // Calculate the number of births, deaths, and immigrants from the given number of years
        int totalBirths = totalOccurrences(birthInterval, years);
        int totalDeaths = totalOccurrences(deathInterval, years);
        int totalImmigrants = totalOccurrences(immigrantInterval, years);
        
        // Calculate the predicted population by adding the births and immigrants and then subtract the deaths
        int predictedPopulation = totalBirths - totalDeaths + totalImmigrants + population;

        // Show the results of the predicted population
        System.out.print("\nThe population in " + years + " years is " + predictedPopulation + "\n");
    };

    // Function that calculates the occurrences of an event given its interval in seconds and the amount of years
    public static int totalOccurrences(int secondInterval, int years) {
            int occurrences = (YEARLY_SECONDS * years) / secondInterval;
            return occurrences;
        };
};
