package excersise.collections;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetExercise {
    public static void main(String[] args) {
        //TODO: create a set of integers
        Set<Integer> integers = new TreeSet<>(); // new HashSet<>();
        // add 5  integers to the set
        integers.add(1);
        integers.add(2);
        integers.add(4);
        integers.add(41);
        integers.add(5);
        // print all integers to console
        integers.forEach(System.out::println);
        // print size of set
        System.out.println("size:"+integers.size());
        // remove one integer from set
        integers.remove(41);

        System.out.println(integers.contains(41));

        // print size of set
        System.out.println("size:"+integers.size());
    }
}
