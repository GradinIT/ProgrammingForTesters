package excersise.inheritence;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Apple extends Fruit implements Packable {
    private final String colour;

    @Override
    public Boolean isEatable() {
        return Boolean.TRUE;
    }

    @Override
    public void sayMyName() {
        System.out.println(this.getClass().getName());
    }
}
