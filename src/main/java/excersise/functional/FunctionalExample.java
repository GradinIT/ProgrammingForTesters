package excersise.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class FunctionalExample {

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(2);
        integers.add(7);
        integers.add(88);
        integers.add(null);
        integers.add(6);
        integers.add(13);

        List<Integer> incrementedValuesList = integers.stream()
                .filter(Objects::nonNull)// elements in the list that is null will not go to next step in Stream
                .filter(integer -> { return integer%2 == 0;}) // elements in the list that is odd will not go to next step in Stream
                .peek(System.out::println)
                .map(FunctionalExample::increment)
                .peek(System.out::println)
                .map(FunctionalExample::decrement)
                .peek(System.out::println)
                .collect(Collectors.toList());

        System.out.println(integers);
        System.out.println(incrementedValuesList);
    }
    private static Long increment(Integer i) {
        long x = i.longValue();
        return ++x;
    }

    private static Integer decrement(Long i) {
        int x = i.intValue();
        return --x;
    }
}
