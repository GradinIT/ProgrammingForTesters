package se.jocke.exercises.basic;



import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class DivideTest {

    Divide divide = new Divide();

    @Test
    public void divideTest(){
        double sum = divide.calculate(10,2);
        Assertions.assertEquals(5.0,sum);

    }

    @Test
    public void divideWithDecimals(){
        double sum = divide.calculate(5,2);
                Assertions.assertEquals(2.5,sum);
    }


}
