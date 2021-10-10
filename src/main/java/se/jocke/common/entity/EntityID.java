package se.jocke.common.entity;

import lombok.ToString;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@ToString
public class EntityID<T> {
    private T id;
    public T getId() {return this.id;}
}
