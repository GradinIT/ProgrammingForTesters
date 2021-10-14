package excersise.inheritence;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class GreenApple extends Apple {
    @Override
    public String toString() {
        return "GreenApple(colour=" + super.getColour() +" isEatable="+ isEatable() +")";
    }
}
