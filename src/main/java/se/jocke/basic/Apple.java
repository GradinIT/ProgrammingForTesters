package se.jocke.basic;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public class Apple extends Fruit {
    @Override
    public Boolean isEatable() {
        return Boolean.TRUE;
    }
}
