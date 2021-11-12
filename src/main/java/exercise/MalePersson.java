package exercise;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class MalePersson extends Person {
    public String getMaleName() {
        return super.getLastName() + ", " + super.getFirstName();
    }
}
