package javaInheritance;

public class Vehicle {
    
    double speed;
    int wheels;

    void go() {
        System.out.println("This vehicle is moving");
    }

    void stop() {
        System.out.println("This vehicle has stopped");
    }

    public void setWheels(int x) {
        this.wheels = x;
    }

    public int getWheels() {
        return wheels;
    }

}
