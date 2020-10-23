package se.jensen.dao;

public class EntityNotFoundException extends RuntimeException {
    public <T>EntityNotFoundException(Integer id) {
        super(String.format("Entity with id %d not found", id));
    }
}
