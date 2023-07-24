package Assessment1.Task1;
import java.util.Scanner;

// Calculates the projected population based on birth, death, and immigration rates.
public class Population {
    // Number of seconds in a year
    final static int YEARLY_SECONDS = 31536000;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter the seconds for every birth: ");
        int birthInterval = input.nextInt();

        System.out.print("Enter the seconds for every death: ");
        int deathInterval = input.nextInt();

        System.out.print("Enter the seconds for every new immigrant: ");
        int immigrantInterval = input.nextInt();

        System.out.print("Enter the number of years: ");
        int years = input.nextInt();

        System.out.print("Enter the current population: ");
        int population = input.nextInt();

        // Calculate total births, deaths, and immigrants over the given years
        int totalBirths = totalOccurrences(birthInterval, years);
        int totalDeaths = totalOccurrences(deathInterval, years);
        int totalImmigrants = totalOccurrences(immigrantInterval, years);
        
        // Predict the population after the given years
        int predictedPopulation = totalBirths - totalDeaths + totalImmigrants + population;

        System.out.println("The population in " + years + " years is " + predictedPopulation);
    };

    /*Calculate the total number of occurrences for an event in a given number of years*/
    public static int totalOccurrences(int secondInterval, int years) {
            int occurrences = (YEARLY_SECONDS * years) / secondInterval;
            return occurrences;
        };
};
