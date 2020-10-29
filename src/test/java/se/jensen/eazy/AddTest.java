package se.jensen.eazy;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.eazy.inteface.Add;

public class AddTest {

    @Test
    public void testStaticAdd() {
        Integer sum = new Add().execute(2,2);
        Assert.assertNotNull(sum);
        Assert.assertEquals(Integer.valueOf(4),sum);
    }
}
