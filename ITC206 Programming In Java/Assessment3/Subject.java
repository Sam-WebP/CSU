package Assessment3;

public class Subject {
    
    String name;
    String code;

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
        return this.code.equals(codeToMatch);
    }

}
