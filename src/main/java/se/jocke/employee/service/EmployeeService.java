package se.jocke.employee.service;

import se.jocke.employee.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getEmployeeById(Integer employeeId);

    Employee createEmployee(Employee employee);

    Employee removeEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    List<Employee> getAllEmployees();

}
