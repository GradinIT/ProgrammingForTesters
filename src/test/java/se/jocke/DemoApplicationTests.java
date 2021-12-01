package se.jocke;

import exercise.Calculator;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class DemoApplicationTests {

    Calculator underTest = new Calculator();

    @Test
    public void itShouldAddNumbers(){

        int numberOne = 30;
        int numberTwo = 30;
        int result = underTest.add(numberOne, numberTwo);

        int expected = 60;

        assertThat(result).isEqualTo(expected);


    }

}
