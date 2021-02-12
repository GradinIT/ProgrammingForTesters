package se.jocke.basic;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public abstract class Fruit implements Eatable {
    private final String name;
}
