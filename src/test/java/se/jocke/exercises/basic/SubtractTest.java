package se.jocke.exercises.basic;

import org.junit.jupiter.api.Assertions;

import org.junit.Test;

public class SubtractTest {

    Subtract subtract = new Subtract();

    @Test
    public void subtractTest(){
        int sum = subtract.calculate(10,2);
        Assertions.assertEquals(8,sum);
    }

    @Test
    public void subtractWithOneNegativeValues(){
        int sum = subtract.calculate(-1,1);
        Assertions.assertEquals(-2,sum);
    }

    @Test
    public void subtractWithTwoNegativeValues(){
        int sum = subtract.calculate(-1, -1);
        Assertions.assertEquals(0,sum);
    }
}
