package exercise.inheritence.realworld;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class PersonInformation implements Validatable{
    private final PersonId id;
    private final ContactInformation[] contactInformations;

    @Override
    public Boolean isValid() {
        Boolean valid = id.isValid();
        if(contactInformations.length != 0 && valid ) {
            for (int i = 0; i < contactInformations.length ; i++) {
                if(!contactInformations[i].isValid()){
                    valid = false;
                    break;
                }
            }
        }
        return valid;
    }
}
