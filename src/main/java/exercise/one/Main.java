package exercise.one;

public class Main {


    public static void main(String[] args) {
        int fistValue = 6;
        int[] arrayOfIntegers = {fistValue, 2, 3, 4, 5};
        String[] arrayOfStrings = {"hej", "på", "dig"};
        int sum = 0;
        for ( int i = 0 ; i < arrayOfIntegers.length ; ++i) {
            sum += arrayOfIntegers[i];
        }
        System.out.println(sum);
        sum = 0;

        sum += arrayOfIntegers[0];
        sum += arrayOfIntegers[1];
        sum += arrayOfIntegers[2];
        sum += arrayOfIntegers[3];
        sum += arrayOfIntegers[4];
        System.out.println(sum);
        int[] cloneOfArrayOfIntegers = arrayOfIntegers.clone();

    }
}
