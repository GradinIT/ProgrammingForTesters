package exercise;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter
@Getter
public class Person {
    private final Integer age;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String ssn;
}