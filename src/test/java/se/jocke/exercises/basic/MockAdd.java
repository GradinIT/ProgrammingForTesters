package se.jocke.exercises.basic;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class MockAdd {

    // skapar en fejk av Add klassen
    @Mock
    private Add mockAdd;

    // Innan mockAdd körs så bestämmer vi vad den ska köra
    @BeforeEach
    public void whenTest() {
        when(mockAdd.calculate(2,2)).thenReturn(9001);
    }


    @Test
    public void MockTest(){

        int sum = mockAdd.calculate(2,3);
        Assert.assertEquals(4,sum);


    }

}
