package excersise.collections;

import java.util.ArrayList;
import java.util.List;

public class ListExercise {
    public static void main(String[] args) {
        //TODO: create a list of integers
        List<Integer> integers = new ArrayList<>();
        // add 5 different integers to the list
        integers.add(10);
        integers.add(33);
        integers.add(12);
        integers.add(5);
        integers.add(1975);
        // print all integers to console
        integers.forEach(System.out::println);
        // print size of list
        System.out.println("size: "+integers.size());
        // remove one integer from list
        integers.remove(integers.indexOf(33));
        // print size of list
        System.out.println("size: "+integers.size());
        integers.forEach(System.out::println);
    }
}
