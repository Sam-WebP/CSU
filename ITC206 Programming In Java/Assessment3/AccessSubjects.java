package Assessment3;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AccessSubjects {
    
    private String filePath;
    private ArrayList<Subject> subjects;

    public AccessSubjects(String filePath) {
        this.filePath = filePath;
        this.subjects = new ArrayList<>();
        readFile();
    }

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

    public void writeToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            for (Subject subject : subjects) {
                pw.println(subject.getName() + "," + subject.getCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Subject> getAllSubjects() {
        return subjects;
    }

    public boolean addSubject(Subject subject) {
        if (subject.isValidCode() && !subject.codeExists(subjects, subject.getCode())) {
            subjects.add(subject);
            return true;
        }
        return false;
    }

}
