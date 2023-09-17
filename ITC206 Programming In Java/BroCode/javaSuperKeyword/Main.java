package javaSuperKeyword;

// super = keyword refers to the superclass (parent) of an object
//         very similar to the "this" keyword, but instead of accessing 
//         attributes from it's own class, it is accessing attributes from the parent.

public class Main {
    
    public static void main(String[] args) {

        Hero hero1 = new Hero("Batman", 42, "$$$");
        Hero hero2 = new Hero("Superman", 43, "everything");


        System.out.println(hero2.toString());
    }

}
