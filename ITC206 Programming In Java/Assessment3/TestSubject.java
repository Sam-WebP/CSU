package Assessment3;



public class TestSubject {
    
    public static void main(String[] args) {

        AccessSubjects savedSubjects = new AccessSubjects("Assessment3/subjects.txt");


        System.out.println("Before changes: " + savedSubjects.getAllSubjects());

        // savedSubjects.addSubject(new Subject("THIS IS A LOL", "ABC420"));
        // savedSubjects.writeToFile();


    }

}
