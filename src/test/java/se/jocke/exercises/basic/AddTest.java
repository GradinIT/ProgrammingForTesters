package se.jocke.exercises.basic;

import org.junit.Assert;
import org.junit.Test;


public class AddTest {

    // 10 + 2 = 12, -5 + 5 = 0 , 6 + -8 = -2

    Add add = new Add();


    @Test
    public void testAddWithPossetiveValues(){
        int sum = add.calculate(10,2);
        Assert.assertEquals(12,sum);
    }

    @Test
    public void testAddWithFirstValueNegative(){
        int sum = add.calculate(-5, 5);
        Assert.assertEquals(0,sum);
    }

    @Test
    public void testAddWithLastValueNegative(){
        int sum = add.calculate(6,-8);
        Assert.assertEquals(-2,sum);
    }






}
