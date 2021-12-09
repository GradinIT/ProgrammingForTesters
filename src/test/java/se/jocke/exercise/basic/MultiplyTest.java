import excersise.Multiply;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class MultiplyTest {
    @Test
    public void multiplyTest1(){
        Assertions.assertEquals(20, Multiply.calculate(10,2));
    }
    @Test
    public void multiplyTest2(){
        Assertions.assertEquals(-5, Multiply.calculate(-1,5));
    }
    @Test
    public void multiplyTest3(){
        Assertions.assertEquals(0, Multiply.calculate(0,5));
    }
}
