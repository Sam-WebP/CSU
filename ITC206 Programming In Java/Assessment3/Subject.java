package Assessment3;

import java.util.ArrayList;

public class Subject {
    
    private String name;
    private String code;

    Subject(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public boolean codeMatches(String codeToMatch) {
        return code.equals(codeToMatch);
    }

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

    // Compares codes from an array of subjects with the code provided through the method's argument
    public boolean codeExists(ArrayList<Subject> allSubjects, String testCode) {
        for (Subject subject : allSubjects) {
            if (testCode.equals(subject.getCode())) {
                return true;
            }
        }
        return false;
    }

}
