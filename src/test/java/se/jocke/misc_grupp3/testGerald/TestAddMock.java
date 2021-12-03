package se.jocke.misc_grupp3.testGerald;

import exercise.unittest.Add;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class TestAddMock {
    @Mock
    Add add = mock(Add.class);

    @Test
    public void testAdd1() {
        when(add.calculate(anyInt(), anyInt())).thenReturn(10);
        Assertions.assertEquals(10, add.calculate(5,5));
    }

    @Test
    public void testAdd2() {
        when(add.calculate(anyInt(), anyInt())).thenReturn(1);
        Assertions.assertEquals(1, add.calculate(-5,6));
    }
    @Test
    public void testAdd3() {
        when(add.calculate(anyInt(), anyInt())).thenReturn(-2);
        Assertions.assertEquals(-2, add.calculate(6,-8));
    }
}