package se.jocke.excersices.basic;

import excersise.unittest.Multiply;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestMultiply {
    @Test
    public void testMultiply1() {
        Assertions.assertEquals(20, Multiply.calculate(10,2));
    }
    @Test
    public void testMultiply2() {
        Assertions.assertEquals(-5, Multiply.calculate(-1,5));
    }
    @Test
    public void testMultiply3() {
        Assertions.assertEquals(0, Multiply.calculate(0,5));
    }
}
