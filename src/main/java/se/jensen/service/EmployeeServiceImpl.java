package se.jensen.service;

import org.springframework.beans.factory.annotation.Autowired;
import se.jensen.dao.EmployeeDao;
import se.jensen.entity.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.logging.Logger;

public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class.getSimpleName());

    @Autowired
    private EmployeeDao employeeDao;

    public Employee getEmployeeById(Integer employeeId) {
        return employeeDao.getEmployee(employeeId);
    }

    public Employee createOrUpdateEmployee(Employee employee) {
        return employeeDao.updateOrCreate(employee);
    }

    public Employee removeEmployee(Employee employee) {
        return employeeDao.delete(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeDao.update(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }
}
