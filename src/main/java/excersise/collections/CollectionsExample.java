package excersise.collections;

import java.util.ArrayList;
import java.util.List;

public class CollectionsExample {
    public static void main(String[] args) {
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
    }
}
