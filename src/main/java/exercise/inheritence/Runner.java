package exercise.inheritence;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        Apple apple = Apple.builder().colour("GUL").build();
        List<Fruit> fruitSalad = new ArrayList<>();
        List<Eatable> rövareOchBanditer = new ArrayList<>();
        fruitSalad.add(apple);
        rövareOchBanditer.add(apple);

    }
}
