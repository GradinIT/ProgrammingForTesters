package se.jensen.eazy;

import org.junit.Test;

public class CarTest {
    @Test
    public void testCarHaveId() {
        Car car = new Car();
        car.setId("my-car");
        car.setHorsePower(280);

        Vehicle vehicle = car;

        vehicle.getId();
    }
}
