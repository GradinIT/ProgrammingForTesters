package se.jocke.common.util;

import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class StringUtil {
    public static String findSubstringStartingWith(String value, String delimiter , String startingWith) {
        String[] array = value.split(delimiter);
        return IntStream.range(0, array.length)
                .mapToObj(i -> array[i])
                .filter(s -> s.startsWith(startingWith))
                .findAny()
                .orElseThrow(()-> new NoSuchElementException(String.format("No element starting with %s found",startingWith)));
    }
    public static String getString(String value, String delimiter, Integer index) {
        String[] array = value.split(delimiter);
        return IntStream.range(0, array.length)
                .filter(i -> i == index)
                .mapToObj(i -> array[i])
                .findAny()
                .orElseThrow(()-> new NoSuchElementException(String.format("No element with index %d found",index)));
    }

    public static String getLine(String value, int lineNumber) {
        return getString(value,"\n",lineNumber);
    }
}
