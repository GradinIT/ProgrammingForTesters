package se.jensen.eazy.inheritence;

import se.jensen.eazy.inteface.Eatable;

public class Apple extends Fruit  implements Eatable {
    @Override
    public Boolean isEatable() {
        return Boolean.TRUE;
    }
}
