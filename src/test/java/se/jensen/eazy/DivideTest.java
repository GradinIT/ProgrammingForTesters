package se.jensen.eazy;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.eazy.inteface.Divide;

public class DivideTest {
    @Test
    public void testDivision() {
        Divide divide = new Divide();
        Integer result = divide.execute(10, 2);
        Assert.assertEquals(Integer.valueOf(5), result);
    }

    @Test(expected = RuntimeException.class)
    public void testErrorCase() {
        Integer result = new Divide().execute(10, 0);
    }
}
