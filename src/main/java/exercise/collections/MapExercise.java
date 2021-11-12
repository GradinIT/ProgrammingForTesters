package exercise.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapExercise {
    public static void main(String[] args) {
        //TODO: create a map with String as key and integer as value
        Map<String,Integer> aMap = new HashMap<>();
        //add 5 key value pairs to the map
        aMap.put("Jocke",100);
        aMap.put("Ove",100);
        aMap.put("Runar",100);
        aMap.put("Carola",100);
        aMap.put("Björn",100);
        // get the keys
        Set<String> keys = aMap.keySet();
        // print keys to console
        keys.forEach(System.out::println);
        // get values
        Collection<Integer> values = aMap.values();
        // print values to console
        values.forEach(System.out::println);
        // change value fo one entry
        aMap.put("Runar",0);
        aMap.values().forEach(System.out::println);

        // skriv bara ut runar
        System.out.println(aMap.get("Runar"));
    }
}
