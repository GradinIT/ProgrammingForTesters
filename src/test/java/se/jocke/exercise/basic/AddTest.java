package se.jocke.exercise.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddTest {

    @Test
    public void testAddFunction() {
        int result = Add.calculate(10, 2);
        Assertions.assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(12 ,result)
        );

        int result2 = Add.calculate(-5, 5);
        Assertions.assertAll(
                () -> assertNotNull(result2),
                () -> assertEquals(0 ,result2)
        );

        int result3 = Add.calculate(6, -8);
        Assertions.assertAll(
                () -> assertNotNull(result3),
                () -> assertEquals(-2 ,result3)
        );
    }
}
