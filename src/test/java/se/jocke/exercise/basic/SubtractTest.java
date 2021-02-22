package se.jocke.exercise.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.exercise.basic.Subtract;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SubtractTest {

    @Test
    public void testSubtractMethod() {
        double result = Subtract.calculate(10, 2);
        Assertions.assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(8 ,result)
        );

        double result2 = Subtract.calculate(-1, 1);
        Assertions.assertAll(
                () -> assertNotNull(result2),
                () -> assertEquals(-2 ,result2)
        );

        double result3 = Subtract.calculate(-1, -1);
        Assertions.assertAll(
                () -> assertNotNull(result3),
                () -> assertEquals(0 ,result3)
        );
    }
}
