package excersise.functional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
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
                    Arrays.asList(line.split(" ")).stream()         // manskriverjuinteihop man skriver ju isär         The the
                            .forEach(word -> {
                                if (!word.equals(" " ) && !word.isEmpty()) {
                                    word = word.toUpperCase();
                                    if (wordCounter.containsKey(word)) {
                                        Integer count = wordCounter.get(word);
                                        wordCounter.put(word, count + 1);
                                    } else {
                                        wordCounter.put(word, 1);
                                    }
                                }
                            });
                });

        wordCounter.entrySet()
            .stream()
            .sorted(Map.Entry.<String, Integer>comparingByValue())
            .limit(20)
            .forEach(System.out::println);
    }
}
