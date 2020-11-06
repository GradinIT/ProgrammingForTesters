package se.jensen.service;

import org.springframework.beans.factory.annotation.Autowired;
import se.jensen.aspects.TimeAndLogg;
import se.jensen.dao.EmployeeDao;
import se.jensen.dao.mapper.EmployeePojoMapper;
import se.jensen.entity.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class.getSimpleName());
    @Autowired
    private EmployeeDao employeeDao;
    @TimeAndLogg
    public Employee getEmployeeById(Integer employeeId) {
        return EmployeePojoMapper.map(employeeDao.findById(employeeId).get());
    }
    @TimeAndLogg
    public Employee createOrUpdateEmployee(Employee employee) {
        return EmployeePojoMapper.map(employeeDao.save(EmployeePojoMapper.map(employee)));
    }
    @TimeAndLogg
    public Employee removeEmployee(Employee employee) {
        employeeDao.delete(EmployeePojoMapper.map(employee));
        return getEmployeeById(employee.getEmployeeId());
    }
    @TimeAndLogg
    public Employee updateEmployee(Employee employee) {
        return EmployeePojoMapper.map(employeeDao.save(EmployeePojoMapper.map(employee)));
    }
    @TimeAndLogg
    public List<Employee> getAllEmployees() {
        return EmployeePojoMapper.map(employeeDao.findAll());
    }
}
