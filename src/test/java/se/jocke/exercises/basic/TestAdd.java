package se.jocke.exercises.basic;

import exercise.unittest.Add;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



public class TestAdd {
    @Test
    public void testAdd1() {
        Assertions.assertEquals(12, Add.calculate(10,2));
    }

    @Test
    public void testadd23(){
           Assertions.assertEquals(0, Add.calculate(-5,5));
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
