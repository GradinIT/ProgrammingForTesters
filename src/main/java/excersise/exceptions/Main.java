package excersise.exceptions;

public class Main {
    public static void main(String[] args)  {
        Person person = Person.builder()
                .firstName("Joakim")
                .lastName("Gradin")
                .build();

        for ( int i = -5 ; i < 2 ; i++) {
            try {
                person.setAge(i);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
            finally {
                System.out.println(person.getAge());
            }
        }
    }
}
