package exercise.inheritence.realworld;

import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@ToString
public class PhoneNumber  extends ContactInformation {
    private final String number;
    @Override
    public Boolean isValid() {
        return true;
    }
}
