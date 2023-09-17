package javaInheritance;

import javaInheritance.Bicycle;
import javaInheritance.Car;

public class Main {

    public static void main(String[] args) {
        Car car = new Car();
        car.go();

        Bicycle bike = new Bicycle();
        bike.stop();

        System.out.println(car.speed);
        System.out.println(bike.speed);

        System.out.println(car.doors + " and " + bike.pedals);

        bike.setWheels(2);
        car.setWheels(4);

        System.out.println("Bike Wheels = " + bike.getWheels());
        System.out.println("Car Wheels = " + car.getWheels());
    }

}
