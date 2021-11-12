package exercise.inheritence.realworld;

import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@ToString
public class HomeAddress extends ContactInformation {
    private final String street;
    private final Integer number;
    private final String city;
    private final Integer postNbr;
    @Override
    public Boolean isValid() {
        return street != null && number != null && city != null && postNbr != null;
    }
}
