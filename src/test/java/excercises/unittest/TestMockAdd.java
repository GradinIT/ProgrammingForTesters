package excercises.unittest;

import exercise.unittest.Add;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestMockAdd {
    Add add = mock(Add.class);
    @Test
    public void testCalculate() {
        when(add.calculate(anyInt(),anyInt())).thenReturn(4);
        Assertions.assertEquals(4,add.calculate(2,2));
    }
}
