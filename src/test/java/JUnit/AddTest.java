package JUnit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddTest {
    @Test
    public void AddTest(){
        Assertions.assertEquals(12,Add.calculate(10,2));



        }
     }