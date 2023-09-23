package javaAbstraction;

public class Main {
    // abstract = abstract classes cannot be instantiated, but they can have a subclass
    //            abstract methods are declared without an implementation. 

    public static void main(String[] args) {
        //Vehicle vehicle = new Vehicle();
        Car car = new Car();

        car.go();
    }
}
