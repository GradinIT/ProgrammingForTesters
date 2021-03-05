package se.jocke.department.entity;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class Entity<ID extends EntityID> {
    private ID id;
}
