package se.jensen.service;

import se.jensen.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getEmployeeById(Integer employeeId);

    Employee createOrUpdateEmployee(Employee employee);

    Employee removeEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    List<Employee> getAllEmployees();
}
