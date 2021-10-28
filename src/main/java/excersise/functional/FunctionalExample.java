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

        Util util = new Util();
        Integer greaterThanOrEqualTo = 7;

        List<Integer> listOfValuesGraterThanOrEqualToSeven = integers.stream()
                .filter(FunctionalExample::checkIfOk) // r is the current integer in the list
                .filter(r -> {
                    return util.isGreaterThanOrEqual(r, greaterThanOrEqualTo);
                })
                .collect(Collectors.toList());


        listOfValuesGraterThanOrEqualToSeven.clear();
        for (Integer i : integers) {
            if (checkIfOk(i)) {
                if (util.isGreaterThanOrEqual(i , greaterThanOrEqualTo)) {
                      listOfValuesGraterThanOrEqualToSeven.add(i);
                }
            }
        }

        int totalValue = integers.stream()
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(totalValue);
    }

    private static boolean checkIfOk(Integer integer) {
  //      System.out.println(" first filter: " + integer);
        // lots of lines of code
        // ..
        // ..
        return Objects.nonNull(integer);
    }
}
