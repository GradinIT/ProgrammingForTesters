package se.jensen.eazy.inheritence;

import se.jensen.eazy.inteface.Eatable;

public class Pear extends Fruit implements Eatable {
    @Override
    public Boolean isEatable() {
        return Boolean.TRUE;
    }
}
