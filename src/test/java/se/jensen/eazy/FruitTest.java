package se.jensen.eazy;

import org.junit.Test;
import se.jensen.eazy.inheritence.Apple;
import se.jensen.eazy.inheritence.Fruit;
import se.jensen.eazy.inheritence.Pear;
import se.jensen.eazy.inteface.Eatable;

import java.util.ArrayList;
import java.util.List;

public class FruitTest {
    @Test
    public void testFruit() {
        List<Fruit> fruitBag = new ArrayList<>();
        fruitBag.add(new Apple());
        fruitBag.add(new Pear());
        List<Eatable> bag = new ArrayList<>();
        for(Fruit fruit: fruitBag){
            bag.add(Eatable.class.cast(fruit));
        }
    }
}
