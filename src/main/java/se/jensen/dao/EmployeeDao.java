package se.jensen.dao;

import se.jensen.entity.Employee;

import java.util.Collection;

public interface EmployeeDao {
    Employee getEmployee(Integer employeeId) throws EntityNotFoundException;

    Collection<Employee> getAllEmployees();

}
