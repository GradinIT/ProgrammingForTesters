package excersise.inheritence.realworld;

public class Runner {
    public static void main(String[] args) {
        PersonId personId = PersonId.builder().id("7102021234").build();

        ContactInformation[] contactInformations = {
                Email.builder().id(personId).address("john.doe@hotmail.com").build(),
                HomeAddress.builder()
                        .city("Stan")
                        .id(personId)
                        .number(4)
                        .postNbr(12345)
                        .street("gatan")
                        .build()
        };

        PersonInformation personInformation = PersonInformation.builder()
                .id(personId)
                .contactInformations(contactInformations)
                .build();

        System.out.println(personInformation);
        System.out.println(personInformation.isValid());
    }
}
