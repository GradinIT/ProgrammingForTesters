package exercise.inheritence;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@Getter
public class Apple extends Fruit implements Eatable {
    private final String colour;

    public Boolean isEatable() {
        return Boolean.TRUE;
    }
}
