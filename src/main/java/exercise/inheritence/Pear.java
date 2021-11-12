package exercise.inheritence;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@ToString
public class Pear extends Fruit {
    private final Boolean peel;
}
