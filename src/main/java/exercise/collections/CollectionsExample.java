package exercise.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

public class CollectionsExample {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();  // Lists ar ordered but not sorted
        Set<Integer> integerSet = new HashSet<>();   // like a list but sorted
        Map<Integer, String> integerStringMap = new HashMap<>();  // list of key/value pair

        Queue<String> queue = new ArrayBlockingQueue<String>(10); // its a queue goe figure ??
        integers.add(1);
        integers.add(0);
        integers.add(5);

        integerSet.add(1);
        integerSet.add(0);
        integerSet.add(5);
        integerSet.add(15);
        integers.forEach(System.out::println); // for each element in the list perform funktion within parentheses
        integerSet.forEach(System.out::println);// for each element in the set perform funktion within parentheses

        integerStringMap.put(85730,"Malmövägen");
        integerStringMap.put(10110,"Typ Sveriges Television");
        integerStringMap.put(45723,"Långgatan");


        System.out.println(integerStringMap.containsKey(85730));
        System.out.println(integerStringMap.keySet());
        System.out.println(integerStringMap.entrySet());
        System.out.println(integerStringMap.containsValue("Malmövägen"));
    }
}
