package se.jensen.dao;

import se.jensen.entity.Employee;

import java.util.Collection;

public interface EmployeeDao {
    Employee getEmployee(Integer employeeId);

    Collection<Employee> getAllEmployees();

    Employee create(Employee employee);
    Employee delete(Employee employee);
    Employee update(Employee employee);
    Employee updateOrCreate(Employee employee);
}
