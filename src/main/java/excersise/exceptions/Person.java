package excersise.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class Person {
    @NonNull
    private final String firstName;
    @NonNull
    private final String lastName;

    private Integer age;

    public void setAge(Integer age) {
        if( age < 0) {
            throw new IllegalArgumentException("Age can't be negative!");
        }
        this.age = age;
    }
}
