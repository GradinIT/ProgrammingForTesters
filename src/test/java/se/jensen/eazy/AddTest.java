package se.jensen.eazy;

import org.junit.Assert;
import org.junit.Test;

public class AddTest {
    @Test
    public void testAddingNumbers() {
        Add add = new Add();
        Integer sum = add.execute(2,2);
        Assert.assertEquals(Integer.valueOf(4),sum);
    }

    @Test
    public void testStaticAdd() {
        Integer sum = Add.execute2(2,2);
        Assert.assertNotNull(sum);
        Assert.assertEquals(Integer.valueOf(4),sum);
    }
    @Test
    public void testStaticStuff() {
        Add.staticIntegerInAdd = 5;
        Add add = new Add();
        Add.staticIntegerInAdd = 14;
        Add add2 = new Add();
        Assert.assertEquals(add.getStaticIntegerInAdd(),
                add2.getStaticIntegerInAdd());
    }
}
