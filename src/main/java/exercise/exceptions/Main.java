package exercise.exceptions;

import java.io.IOException;

public class Main {
    public static void main(String[] args)  {
        Person person = Person.builder()
                    .firstName("Joakim")
                    .lastName("Gradin")
                    .build();

        try {
            person.setAge(4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
