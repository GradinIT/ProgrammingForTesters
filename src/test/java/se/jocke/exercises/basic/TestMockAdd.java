package se.jocke.exercises.basic;

import com.google.common.annotations.VisibleForTesting;
import com.google.errorprone.annotations.DoNotMock;
import exercise.unittest.Add;
import org.checkerframework.dataflow.qual.TerminatesExecution;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;


public class TestMockAdd {
    @Test
            public void testCalculate(){
        Add adde = mock(Add.class);

    }
            
    
    
}
