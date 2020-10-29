package se.jensen.service;

import se.jensen.api.EmployeeModel;
import se.jensen.entity.Employee;

public interface EmployeeService {
    Employee getEmployeeById(Integer employeeId);

    Employee createOrUpdateEmployee(Employee employee);
}
