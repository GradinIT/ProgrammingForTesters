package exercise.one;

import exercise.two.lombok.MySecondClassLombok;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        MySecondClassLombok mySecondClassLombok = MySecondClassLombok.builder()
                .age(1)
                .firstName("Lennart")
                .lastName("Svensson")
                .build();

        MySecondClassLombok mySecondClassLombok2 = new MySecondClassLombok("Lennart",
                "Svensson",
                3);

    }
}
