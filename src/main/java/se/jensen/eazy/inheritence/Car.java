package se.jensen.eazy.inheritence;

public class Car extends Vehicle {
    private Integer HorsePower;

    public Car(Integer horsePower, String id) {
        super(id);
        HorsePower = horsePower;

    }

    public Integer getHorsePower() {
        return HorsePower;
    }

    public void setHorsePower(Integer horsePower) {
        HorsePower = horsePower;
    }
}
