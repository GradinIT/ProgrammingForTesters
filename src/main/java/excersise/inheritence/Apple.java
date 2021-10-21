package excersise.inheritence;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Apple extends Fruit implements Eatable{
    private final String colour;

    public Boolean isEatable() {
        return Boolean.TRUE;
    }
}