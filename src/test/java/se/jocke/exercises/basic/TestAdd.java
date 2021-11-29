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

    @Test
    public void testAdd50() {
        Assertions.assertEquals(50, Add.calculate(25, 25));
    }
    @Test
    public void testAdd20() {
        Assertions.assertEquals(22, Add.calculate(12, 11));
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
