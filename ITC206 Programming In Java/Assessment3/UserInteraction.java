package Assessment3;

import java.util.Scanner;

public class UserInteraction {
    
    private AccessSubjects savedSubjects;
    private Scanner scanner = new Scanner(System.in);

    public UserInteraction(String filePath) {
        this.savedSubjects = new AccessSubjects(filePath);
    }

    public void displayAllSubjects() {
        System.out.println(savedSubjects.getAllSubjects());
    }

    public boolean addNewSubject() {
        System.out.println("Enter the details of the new subject below:");

        System.out.print("Subject Name: ");
        String subjectName = scanner.nextLine();

        System.out.print("Subject Code: ");
        String subjectCode = scanner.nextLine();

        Subject newSubject = new Subject(subjectName, subjectCode);

        return savedSubjects.addSubject(newSubject);
    }

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

    public void startInteraction() {
        System.out.println("list of saved subjects:");
        displayAllSubjects();
        
        boolean userFinished = false; // Outer while loop flag

        while (!userFinished && userChoice("Do you want to add a new subject?")) {
            boolean validInput = false; // Inner while loop flag
            while (!validInput) {
                validInput = addNewSubject();
                if (!validInput) { // If there was an error with the code entered:
                    if (!userChoice("Incorrect Code format. Do you want to try again?")) { // If the user doesn't want to continue:
                        userFinished = true;   
                        break;
                    }   
                }
            }
        }
        savedSubjects.writeToFile();
    }

}
