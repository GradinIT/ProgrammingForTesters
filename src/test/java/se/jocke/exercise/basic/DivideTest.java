import excersise.Divide;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class DivideTest {
    @Test
    public void testDivide1(){
        Assertions.assertEquals(5, Divide.divide(10,2));
    }
    @Test
    public void testDivide2(){
        Assertions.assertEquals(5, Divide.divide(30,2));
    }
}
