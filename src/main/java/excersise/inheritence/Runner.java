package excersise.inheritence;

public class Runner {
    public static void main(String[] args) {



        Fruit[] fruits = {
                Apple.builder().colour("Blue").build(),
                GreenApple.builder().colour("Green").build(),
                Pear.builder().peel(true).build()};

        Etable[] eatables = {
                Apple.builder().colour("Yellow").build(),
                GreenApple.builder().colour("Green").build(),
                Pear.builder().peel(false).build()};


        for( int i = 0 ; i < fruits.length ; ++i) {
            if(fruits[i] instanceof Apple ) {
                System.out.println("index:" + i + " is an apple");
            }
            System.out.println("index:" + i + " value:" + fruits[i]);
            fruits[i].sayMyName();
            fruits[i].sayMyNameAgain("Jocke");
        }
        for( int i = 0 ; i < eatables.length ; ++i) {
            if(eatables[i] instanceof Apple ) {
                System.out.println("index:" + i + " is an apple");
            }
            System.out.println("index:" + i + " value:" + eatables[i]);
        }
    }
}
