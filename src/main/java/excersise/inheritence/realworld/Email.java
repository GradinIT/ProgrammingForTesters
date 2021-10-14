package excersise.inheritence.realworld;

import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@ToString
public class Email extends ContactInformation {
    private final String address;
    @Override
    public Boolean isValid() {
        return address.contains("@");
    }
}





























