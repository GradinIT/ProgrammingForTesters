package se.jocke;

import excersise.Subtract;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class SubtractTest {
        @Test
        public void SubtractTest1(){
            Assertions.assertEquals(2, Subtract.subtract(10,5));
        }
    }

