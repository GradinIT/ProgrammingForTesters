package excersise.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class CollectionsExample {
    public static void main(String[] args) {
      /*
        List<String> myList = new ArrayList<>();
        myList.add("Runar");
        myList.add("Runar");
        myList.add("Carola");

        System.out.println(myList.size());
        System.out.println(myList);

        for (int i = 0 ; i < myList.size() ; i++) {
            System.out.println(myList.get(i));
        }
        for (String name : myList) {
            System.out.println(name);
        }

        myList.stream().forEach(System.out::println);


        Set<Integer> integerSet = new TreeSet<>();
        integerSet.add(2);
        integerSet.add(1);
        integerSet.add(0);
        integerSet.add(17);
        integerSet.forEach(System.out::println);
*/

        Map<String,Integer> wordCounter = new HashMap<>();
        wordCounter.put("the",1234);
        wordCounter.put("They",888);

        System.out.println(wordCounter.containsKey("the"));

    }
}
