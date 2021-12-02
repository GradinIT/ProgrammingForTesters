package se.jocke.exercises.basic;

import exercise.unittest.Add;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class TestAdd {
    Add add = new Add();
    @Test
    public void testAdd1() {
        Assertions.assertEquals(12, add.calculate(10,2));
    }

    //Meddelande från Erwin

    @Test
    public void testAdd2() {
        Assertions.assertEquals(0, add.calculate(-5,5));
    }
    @Test
    public void testAdd3() {
        Assertions.assertEquals(-2, add.calculate(6,-8));
    }
}
