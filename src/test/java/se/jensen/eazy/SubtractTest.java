package se.jensen.eazy;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.eazy.inteface.Subtract;

public class SubtractTest {
    @Test
    public void testSubtraction() {
        Integer result = new Subtract().execute(10,4);
        Assert.assertEquals(Integer.valueOf(6),result);
    }
}
