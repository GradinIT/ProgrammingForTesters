package se.jocke.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCalculator {
    private Calculator calculator = new Calculator();

    @Test
    public void testMultiplycation() {
        int product = calculator.multiply(4,2);
        Assertions.assertEquals(8,product);
    }
    @Test
    public void testNegativeMultiplycation() {
        int product = calculator.multiply(4,-2);
        Assertions.assertEquals(-8,product);
    }
    @Test
    public void testDubbelNegativeMultiplycation() {
        int product = calculator.multiply(-4,-2);
        Assertions.assertEquals(8,product);
    }
}
