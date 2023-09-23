package Assessment3.Task1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AccessSubjects {
    
    // Declare the variables for the file path and array list of saved subject 
    private String filePath;
    private ArrayList<Subject> subjects;

    // Constructor for the relevant attributes to read and write data to and from a file of subjects
    public AccessSubjects(String filePath) {
        this.filePath = filePath;
        this.subjects = new ArrayList<>();
        readFile(); // Read and move data from the file to the subjects array list
    }

    // Read subjects from the file and add them to the subjects array list
    public void readFile() {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] half = line.split(",");
                subjects.add(new Subject(half[0], half[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Write subjects from the array list back to the file related to the object
    public void writeToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for (Subject subject : subjects) {
                pw.println(subject.getName() + "," + subject.getCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getter method to retrieve the subjects list
    public ArrayList<Subject> getAllSubjects() {
        return subjects;
    }

    // Validates that the subject code is valid and then adds the subject to the list
    public boolean addSubject(Subject subject) {
        if (subject.isValidCode() && !subject.codeExists(subjects, subject.getCode())) {
            subjects.add(subject);
            return true;
        }
        return false;
    }

}
