package se.jensen.eazy;

import org.junit.Test;
import se.jensen.eazy.inheritence.Car;
import se.jensen.eazy.inheritence.Vehicle;

public class CarTest {
    @Test
    public void testCarHaveId() {

        Car car = new Car(); // Create a variable of type Car named car and initialize it to point att a new Instance
        car.setId("my-car"); // set id to the car by using the inherited metod setId from the super-class Vehicle
        car.setHorsePower(280); // set HorsePower by using the setHorsePower method defined in Car class

        Vehicle vehicle = car;
        System.out.println(vehicle.getClass().getName()); // print the name of the Instance that vehicke points to

        vehicle.getId();

        if(vehicle instanceof Car) { // check if vehicle is of typ Car
            System.out.println("Vehicle is a Car so we can type cast the vehicle reference to ty typ Car ");
            Car otherCar = (Car)vehicle;
        }
        if(Car.class.isInstance(vehicle)){ // check if vehicle is of typ Car
            System.out.println("Vehicle is a Car so we can type cast the vehicle reference to ty typ Car ");
            Car otherCar = (Car)vehicle;
        }
    }
}
