package exercise.exceptions;

import exercise.inheritence.realworld.ContactInformation;
import exercise.inheritence.realworld.PersonId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.io.IOException;
import java.util.List;

@Builder
@Getter
@ToString
@EqualsAndHashCode
public class Person {
    @NonNull
    private final String firstName;
    @NonNull
    private final String lastName;

    private final PersonId id;

    private Integer age;

    public void setAge(Integer age) throws IOException {
        if( age >= 6) {
            this.age = age;
        }
        else {
            throw new IOException("age can't be less than 6");
        }
    }
    private final List<ContactInformation> contactInformations;
}
