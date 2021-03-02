package se.jocke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.basic.Calculator;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestMe {

    @Mock private Calculator calculator;

    @BeforeEach
    public void setUp(){
        when(calculator.getName()).thenReturn("TestMe");
    }

    @Test
    public void test(){
        List<String> mockedList = mock(List.class);
        mockedList.size();
        verify(mockedList, times(1)).size();
    }

    @Test
    public void testGetCalculatorName(){
        Assertions.assertEquals("TestMe", calculator.getClass().getName());
    }

}
