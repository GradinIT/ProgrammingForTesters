package excersise;

import java.util.*;

public class SetExcersice1 {

    public static void main(String[] args) {

     /*   System.out.println("Set size:");
        Set<Integer> integerSet = new HashSet<>();

        // TreeSet / HashSet
        integerSet.add(1);
        integerSet.add(2);
        integerSet.add(4);
        integerSet.add(4);
        integerSet.add(5);
        integerSet.forEach(System.out::println);
        System.out.println("Size: " + integerSet.size());
        integerSet.remove(5);
        System.out.println("Size: " + integerSet.size());
        integerSet.forEach(System.out::println);
        System.out.println(integerSet.contains(7));

*/

        Map<String, Integer> aMap = new HashMap<>();
        aMap.put("forsta", 32);
        aMap.put("andra", 34);
        aMap.put("imitten", 55);
        aMap.put("three", 35);
        aMap.put("tva", 2);

        System.out.println(aMap.containsKey("ads2"));
        Set<String> keys = aMap.keySet();

        keys.forEach(System.out::println);

        System.out.println(aMap.get(aMap));

        System.out.println();

        Collection  <Integer> values = aMap.values();
        values.forEach(System.out::println);
        aMap.put("Runar", 0);
        aMap.values().forEach(System.out::println);


        for (String key : aMap.keySet()) {
            System.out.println(key);

        }

            for (int i = 0; i < 5; i++) {
                System.out.println(aMap.toString());
            }

        System.out.println();

        Set keys2 = aMap.keySet();
        System.out.println("All keys are: " + keys2);
        System.out.println(aMap.get("Runar"));
// To get all key: value

    }
}