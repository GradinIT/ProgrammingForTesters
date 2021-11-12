package exercise.inheritence.realworld;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class PersonId implements Validatable{
    private final String id;
    @Override
    public Boolean isValid() {
        return id.length() > 9 && id.length() < 13 ;
    }
}
