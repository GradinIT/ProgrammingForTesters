package exercise.exceptions;

import java.io.IOException;

public class Main {
    public static void main(String[] args)  {
        Person person = Person.builder()
                    .firstName("Joakim")
                    .lastName("Gradin")
                    .build();

        try {
            // här kodar vi som om inget går fel

            person.setAge(4);

            // faktiskt ända hit
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e) {

        }
    }
}
