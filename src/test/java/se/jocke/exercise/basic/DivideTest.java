package se.jocke.exercise.basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.exercise.basic.Divide;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DivideTest {

    @Test
    public void testDivdeMethod() {
        double result = Divide.calculate(10, 2);
        Assertions.assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(5 ,result)
        );

        double result2 = Divide.calculate(5, 2);
        Assertions.assertAll(
                () -> assertNotNull(result2),
                () -> assertEquals(2.5 ,result2)
        );
    }
}
