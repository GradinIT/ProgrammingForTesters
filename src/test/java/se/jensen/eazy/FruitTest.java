package se.jensen.eazy;

import org.junit.Test;
import se.jensen.eazy.inheritence.Apple;
import se.jensen.eazy.inheritence.Fruit;
import se.jensen.eazy.inteface.Eatable;

import java.util.ArrayList;
import java.util.List;

public class FruitTest {
    @Test
    public void testFruit() {
        List<Fruit> fruitBag = new ArrayList<>();
        fruitBag.add(new Apple());
        List<Eatable> bag = new ArrayList<>();
        bag.add(Eatable.class.cast(fruitBag.get(0)));
    }
}
