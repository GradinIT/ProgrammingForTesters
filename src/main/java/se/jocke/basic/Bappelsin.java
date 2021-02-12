package se.jocke.basic;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Bappelsin extends Fruit {
    @Override
    public Boolean isEatable() {
        return Boolean.FALSE;
    }
}
