package se.jocke.exercises.basic;

import exercise.unittest.Subtract;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestSubtract {
    @Test
    public void testSubtract1() {
        Assertions.assertEquals(8, Subtract.calculate(10,2));
    }
    @Test
    public void testSubtract2() {
        Assertions.assertEquals(-2, Subtract.calculate(-1,1));
    }
    @Test
    public void testSubtract3() {
        Assertions.assertEquals(0, Subtract.calculate(-1,-1));
    }

    public static class TestNinja {
    }
}
