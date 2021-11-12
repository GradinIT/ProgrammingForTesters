package exercise.inheritence;

public class Runner {
    public static void main(String[] args) {

        Fruit[] fruits = {
                Apple.builder().colour("Blue").build(),
                GreenApple.builder().colour("Green").build(),
                Pear.builder().peel(true).build()};

        Eatable[] eatables = {
                Apple.builder().colour("Yellow").build(),
                GreenApple.builder().colour("Green").build(),
                Potato.builder().build()};

    }
}
