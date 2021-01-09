package se.jensen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jensen.aspects.TimeAndLogg;
import se.jensen.dao.DepartmentDao;
import se.jensen.dao.EmployeeDao;
import se.jensen.dao.EmployeeDatabaseEntry;
import se.jensen.dao.EntityNotFoundException;
import se.jensen.dao.mapper.EmployeePojoMapper;
import se.jensen.entity.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    @TimeAndLogg
    public Employee getEmployeeById(Integer employeeId) {
        Optional<EmployeeDatabaseEntry> employee = employeeDao.findById(employeeId);
        if(employee.isPresent())
            return EmployeePojoMapper.map(employee.get());
        else throw new EntityNotFoundException(employeeId);
    }
    @TimeAndLogg
    public Employee createOrUpdateEmployee(Employee employee) {
        return EmployeePojoMapper.map(employeeDao.save(EmployeePojoMapper.map(employee)));
    }
    @TimeAndLogg
    public Employee removeEmployee(Employee employee) {
        employeeDao.delete(EmployeePojoMapper.map(employee));
        return employee;
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
