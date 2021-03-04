package se.jocke.exercises.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.Test;

public class MultiplyTest {

    se.jocke.exercises.basic.multiply multiply = new multiply();

    @Test
    public void TestMultiply(){
        int sum = multiply.calculate(10, 2);
        Assertions.assertEquals(20,sum);
    }

    @Test
    public void TestMultiplyWithOneNegativeValue(){
        int sum = multiply.calculate(-1,5);
        Assertions.assertEquals(-5,sum);
    }

    @Test
    public void TestMultiplyWithZero(){
        int sum = multiply.calculate(0,5);
        Assertions.assertEquals(0,sum);
    }
}
