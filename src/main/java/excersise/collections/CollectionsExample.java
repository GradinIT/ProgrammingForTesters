package excersise.collections;

import com.google.common.collect.Maps;
import excersise.exceptions.Person;
import excersise.inheritence.realworld.Email;
import excersise.inheritence.realworld.HomeAddress;
import excersise.inheritence.realworld.PersonId;
import excersise.inheritence.realworld.PhoneNumber;

import java.util.List;
import java.util.Map;

public class CollectionsExample {
    public static void main(String[] args) {
        Person person = Person.builder()
                .id(PersonId.builder()
                        .id("197510101234")
                        .build()
                )
                .firstName("Joakim")
                .lastName("Gradin")
                .contactInformations(
                        List.of(
                                PhoneNumber.builder()
                                        .id(PersonId.builder()
                                                .id("197510101234")
                                                .build()
                                        )
                                        .number("+4670123456789").build(),
                                Email.builder()
                                        .id(PersonId.builder()
                                                .id("197510101234")
                                                .build()
                                        )
                                        .address("john.doe@hotbrev.se").build(),
                                Email.builder()
                                        .id(PersonId.builder()
                                                .id("197510101234")
                                                .build()
                                        )
                                        .address("john.doe@gmail.se").build(),
                                HomeAddress.builder()
                                        .id(PersonId.builder()
                                                .id("197510101234")
                                                .build())
                                        .number(123)
                                        .street("wrong street")
                                        .postNbr(12345)
                                        .city("Sin City")
                                        .build()

                        ))
                .build();

       // System.out.println(person);


        Map<PersonId,Person> personMap = Maps.newHashMap();
        personMap.put(person.getId(),person);

        System.out.println(personMap.containsKey(PersonId.builder().id("123").build()));

        System.out.println(personMap.values());
        System.out.println(personMap.keySet());
        System.out.println(personMap.entrySet());
    }
}
