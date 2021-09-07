package se.jocke;

import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class FizzBuzz {
    static <STRING> IntFunction<STRING> fizzBuzzModuloCheck(int m, STRING string, IntFunction<STRING> f) {
        return (int i) -> (i % m == 0) ? string : f.apply(i);
    }
    public static void main(String[] args) {
        IntStream.rangeClosed(1, 10000000)
                .mapToObj(
                        fizzBuzzModuloCheck(15, "FizzBuzz",
                            fizzBuzzModuloCheck(5, "Buzz",
                                fizzBuzzModuloCheck(3, "Fizz", Integer::toString))))
                .forEach(s -> System.out.printf("%s, ",s));
    }
}