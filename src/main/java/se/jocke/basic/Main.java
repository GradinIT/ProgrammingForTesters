package se.jocke.basic;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        String str = returnAString();
        if( str != null) {
            str.equals("hello");
        }
        Optional<String> stringOptional = getOptionalString();
        if(stringOptional.isPresent()) {
            String s = stringOptional.get();
            s.equals("hello");
        }
    }
    static Optional<String> getOptionalString() {
        return Optional.empty();
    }
    static String returnAString() {
        return null;
    }
}
