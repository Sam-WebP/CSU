package Assessment3.Task1;

import java.util.Scanner;

public class UserInteraction {
    
    // Declare an AccessSubjects object variable and a scanner variable
    private AccessSubjects savedSubjects;
    private Scanner scanner = new Scanner(System.in);

    // Constructor to initialise the file that the user will be interacting with
    public UserInteraction(String filePath) {
        this.savedSubjects = new AccessSubjects(filePath);
    }

    // Shows the user all saved subjects
    public void displayAllSubjects() {
        System.out.println("\nlist of saved subjects:");
        System.out.println(savedSubjects.getAllSubjects());
    }

    // Get the required data from the user to add a new subject
    public boolean addNewSubject() {
        System.out.println("\nEnter the details of the new subject below:");

        System.out.print("Subject Name: ");
        String subjectName = scanner.nextLine();

        System.out.print("Subject Code: ");
        String subjectCode = scanner.nextLine();

        Subject newSubject = new Subject(subjectName, subjectCode);

        return savedSubjects.addSubject(newSubject);
    }

    // Gets a yes or no answer from the user about a question
    public boolean userChoice(String question) {
        while (true) {
            System.out.print(question + " (yes/no) ");
            String answer = scanner.nextLine().trim().toLowerCase();

            if (answer.equals("yes") || answer.equals("y")) {
                return true;
            } else if (answer.equals("no") || answer.equals("n")) {
                return false;
            } else {
                System.out.println("Invalid response. Only answer 'yes' or 'no'.");
            }
        }
    }

    // Control the logic between the user and the program
    public void startInteraction() {
        boolean userFinished = false; // Outer while loop flag

        while (!userFinished && userChoice("\nDo you want to add a new subject?")) {
            boolean validInput = false; // Inner while loop flag
            while (!validInput) {
                displayAllSubjects(); // Display subjects to the user before they add a subject
                validInput = addNewSubject(); // Ask the user to add a subject
                if (!validInput) { // If there was an error with adding the subject
                    if (!userChoice("Incorrect Code format. Do you want to try again?")) { // If the user doesn't want to try again:
                        userFinished = true; // End the outer loop flag
                        break; // Break out of the inner loop
                    }
                }
            }
        }
        savedSubjects.writeToFile(); // Writes subjects back to the file from the array list
    }

}
