package se.jocke.exercises.basic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.mockito.MockedStatic;



public class MockSubtract {

    @Test
    public void mockSubtractTest(){

        MockedStatic<Subtract> mockedStatic = Mockito.mockStatic(Subtract.class);
        mockedStatic.when(() -> Subtract.calculate(4,1)).thenReturn(9002);
        Assertions.assertEquals(3, Subtract.calculate(4,1));
    }
}
