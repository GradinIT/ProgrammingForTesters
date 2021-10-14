package excersise.inheritence.realworld;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class ContactInformation implements Validatable{
    private final PersonId id;
}
