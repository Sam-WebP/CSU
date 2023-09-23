package Assessment3.Task1;

import java.util.ArrayList;

public class Subject {
    
    // Declaring variables for the name and code of the subject
    private String name;
    private String code;

    // Constructor to initiate the name and the code
    Subject(String name, String code) {
        this.name = name;
        this.code = code;
    }

    // Subject name getter method
    public String getName() {
        return name;
    }

    // Subject code getter method
    public String getCode() {
        return code;
    }

    // Method that checks of a provided code matches the objects code
    public boolean codeMatches(String codeToMatch) {
        return code.equals(codeToMatch);
    }

    // Override the toString method to show the subjects details
    @Override
    public String toString() {
        return code + " " + name;
    }

    // Versatile method using an argument to determine if it has the correct format
    public boolean isValidCode(String testCode) {
        return testCode != null && testCode.matches("^[A-Za-z]{3}[0-9]{3}$");
    }

    // Overloaded method using the objects code to determine if it has the correct format
    public boolean isValidCode() {
        return code != null && code.matches("^[A-Za-z]{3}[0-9]{3}$");
    }

    // Checks if a given code already exists within an array list of subjects
    public boolean codeExists(ArrayList<Subject> allSubjects, String testCode) {
        for (Subject subject : allSubjects) {
            if (testCode.equals(subject.getCode())) {
                return true;
            }
        }
        return false;
    }

}
