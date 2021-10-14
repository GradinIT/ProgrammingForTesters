package excersise.inheritence;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@Getter
@ToString
public abstract class Fruit implements Etable {
    public abstract void sayMyName();
    public void sayMyNameAgain(String name) {
        System.out.println(name);
    }
}
