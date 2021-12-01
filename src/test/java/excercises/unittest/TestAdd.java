package excercises.unittest;

import exercise.unittest.Add;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestAdd {
    @Test
    public void testCalculate() {
        Add add = new Add();
        Assertions.assertEquals(4, add.calculate(2,2));
    }
}
