package se.jensen.dao;

import se.jensen.entity.Employee;

public class EntityAlreadyInStorageException extends RuntimeException {
    public EntityAlreadyInStorageException(Employee employee) {
        super(String.format("Entity with id %d already in storage",employee.getEmployeeId()));
    }
}
