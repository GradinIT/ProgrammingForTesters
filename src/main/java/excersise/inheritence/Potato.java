package excersise.inheritence;

import lombok.Builder;

@Builder
public class Potato implements Eatable{
    @Override
    public Boolean isEatable() {
        return Boolean.TRUE;
    }
}
