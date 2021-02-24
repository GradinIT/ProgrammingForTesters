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
    @Test
    public void testAddition(){
        int product = calculator.addition(10, 2);
        Assertions.assertEquals(12,product);
    }
    @Test
    public void testAddition1(){
        int product1 = calculator.addition(5,5);
        Assertions.assertEquals(10,product1);
    }
    @Test
    public void testAddition2(){
        int product2 = calculator.addition(6,8);
        Assertions.assertEquals(14,product2);
    }
    @Test
    public void testDivide(){
        double product = calculator.divide(10,2);
        Assertions.assertEquals(5,product);
    }
    @Test
    public void testDivide1(){
        double product = calculator.divide(5,2);
        Assertions.assertEquals(2.5,product);
    }

}
