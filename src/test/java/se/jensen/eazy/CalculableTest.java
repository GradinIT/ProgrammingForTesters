package se.jensen.eazy;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.jensen.eazy.inteface.Add;
import se.jensen.eazy.inteface.Calculable;
import se.jensen.eazy.inteface.Divide;
import se.jensen.eazy.inteface.Multiply;
import se.jensen.eazy.inteface.Subtract;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

public class CalculableTest {
    List<Calculable> calculableList = new ArrayList<>();
    @Before
    public void setUp() {
        calculableList.add(new Add());
        calculableList.add(new Subtract());
        calculableList.add(new Multiply());
        calculableList.add(new Divide());
    }

    @Test
    public void testAll() {
        for (Calculable calculable : calculableList) {
            Assert.assertNotNull(calculable.execute(10,2));
        }
    }
    @Test(expected = NullPointerException.class)
    public void testException() {
        calculableList.add(null);
        for (Calculable calculable : calculableList) {
            Assert.assertNotNull(calculable.execute(10,2));
        }
    }
    @Test
    public void testRuntimExceptio() {
        try {
            for (Calculable calculable : calculableList) {
                Assert.assertNotNull(calculable.execute(10, 0));
            }
            fail("expected RuntimeException");
        }
        catch (RuntimeException runtimeException) {
            Assert.assertEquals("cant be 0" , runtimeException.getMessage());
        }
        catch (Exception e) {
            fail("expected RuntimeException");
        }
    }
}
