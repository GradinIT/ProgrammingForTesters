package se.jocke.common.dao;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Integer id) {
        super(String.format("Entity with id %d not found", id));
    }
}
