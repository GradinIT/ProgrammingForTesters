package se.jocke.exercises.basic;

import exercise.unittest.Divide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestDivide {
    @Test
    public void testDivide1() {
        Assertions.assertEquals(5, Divide.calculate(10,2));
    }
    @Test
    public void testDivide2() {
        Assertions.assertEquals(2.5, Divide.calculate(5,2));
    }
    @Test
    public void testDivideByZero() {
        Assertions.assertThrows(ArithmeticException.class, () ->Divide.calculate(10,0));
    }
}
