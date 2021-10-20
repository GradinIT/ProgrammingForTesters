package excersise;

public class Runner {
    public static void main(String[] args) {

        Person person = Person.builder()
                .firstName("Arne")
                .lastName("Anka")
                .age(0)
                .middleName("")
                .ssn("19520101-1234")
                .build();

        Person person2 = Person.builder()
                .firstName("Arne")
                .lastName("Anka")
                .age(0)
                .middleName("")
                .ssn("19520101-1234")
                .build();

        MalePersson malePersson = MalePersson.builder()
                .firstName("Arne")
                .lastName("Anka")
                .age(0)
                .middleName("")
                .ssn("19520101-1234")
                .build();

        System.out.println(malePersson.getMaleName());



        String hashCode = person.toString();
        System.out.println(hashCode);

        System.out.println(hashCode.split("@")[0]);
        System.out.println(hashCode.split("@")[1]);

        System.out.println(hashCode.substring(hashCode.lastIndexOf("@")+1));
    }
}
