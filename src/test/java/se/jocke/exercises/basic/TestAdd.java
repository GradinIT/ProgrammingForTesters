package se.jocke.exercises.basic;

import exercise.unittest.Add;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TestAdd {
    @Test
    public void testAdd1() {
        Assertions.assertEquals(12, Add.calculate(10,2));
    }
    public void thing(){
        System.out.println("thing");
    }
    @Test
    public void testAdd2() {
        Assertions.assertEquals(0, Add.calculate(-5,5));
    }
    @Test
    public void testAdd3() {
        Assertions.assertEquals(-2, Add.calculate(6,-8));
    }
}
