package se.jocke.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jocke.aspects.TimeAndLogg;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.dao.EntityNotFoundException;
import se.jocke.dao.mapper.EmployeePojoMapper;
import se.jocke.entity.Employee;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

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
