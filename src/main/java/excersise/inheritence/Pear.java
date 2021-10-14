package excersise.inheritence;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@ToString
public class Pear extends Fruit{
    private final Boolean peel;

    @Override
    public Boolean isEatable() {
        return Boolean.TRUE;
    }

    @Override
    public void sayMyName() {
        System.out.println(this.getClass().getSimpleName());
    }
}
