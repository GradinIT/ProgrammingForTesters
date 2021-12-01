package se.jocke.exercises.basic;

import exercise.unittest.Add;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TestAdd {
    @Test
    public void testAdd1() {
        Add add = new Add();
        Assertions.assertEquals(12, add.calculate(10,2));
    }
    public void thing(){
        System.out.println("hej");
    }
    @Test
    public void testAdd2() {
        Add add = new Add();
        Assertions.assertEquals(0, add.calculate(-5,5));
    }
    @Test
    public void testAdd3()
    {
        Add add = new Add();
        Assertions.assertEquals(-2, add.calculate(6,-8));
    }
}
