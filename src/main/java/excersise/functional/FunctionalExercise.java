package excersise.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FunctionalExercise {
    public static void main(String[] args) {
        // Create a list of integers
        List<Integer> integers = new ArrayList<>();
        integers.add(2);
        integers.add(7);
        integers.add(88);
        integers.add(9);
        integers.add(6);
        integers.add(13);

        // fill it with random integer values , add some null value
        // create a stream for the list
        // add a null filter ti the stream TIP ( Use Objects::nonNull)
        // calculate the total sum of the integers in your list
        // print the sum to console
        int totalSum = integers.stream()
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("the sum of all integers: " + totalSum);

    }
}
