package exercise.functional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class WordCounter {
    private static final String fileName = "https://raw.githubusercontent.com/jraleman/42_get_next_line/master/tests/hhgttg.txt";
    static Map<String, Integer> wordCounter = new HashMap<>();

    public static void main(String[] args) throws IOException {
        URL url = new URL(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        reader.lines()
                .filter(Objects::nonNull)      // filter null lines
                .forEach(line -> {
                    Arrays.asList(line.split(" ")).stream()
                            .filter(word -> !word.isEmpty())
                            .map(String::toUpperCase)
                            .map(word -> word.replaceAll("[^a-zA-Z]", ""))
                            .forEach(word -> {
                                wordCounter.compute(word,(key,value)->{
                                    if(value == null)
                                        value = 1;
                                    else
                                        value++;
                                    return value;
                                });
                               
                            });
                });

        wordCounter.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(20)
                .forEach(System.out::println);
    }
}
