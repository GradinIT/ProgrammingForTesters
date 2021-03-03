package se.jocke.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jocke.aspects.TimeAndLogg;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.dao.EntityAlreadyInStorageException;
import se.jocke.dao.EntityNotFoundException;
import se.jocke.dao.mapper.EmployeePojoMapper;
import se.jocke.department.entity.Employee;

import java.util.List;
import java.util.Optional;

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
    public Employee createEmployee(Employee employee) {
        Optional<EmployeeDatabaseEntry> e = employeeDao.findById(employee.getEmployeeId().getId());
        if(e.isPresent()) {
            throw new EntityAlreadyInStorageException(employee.getEmployeeId().getId());
        }
        return EmployeePojoMapper.map(employeeDao.save(EmployeePojoMapper.map(employee)));
    }


    @TimeAndLogg
    public Employee removeEmployee(Employee employee) {
        employeeDao.delete(EmployeePojoMapper.map(employee));
        return employee;
    }
    @TimeAndLogg
    public Employee updateEmployee(Employee employee) {
        Optional<EmployeeDatabaseEntry> e = employeeDao.findById(employee.getEmployeeId().getId());
        if(!e.isPresent()) {
            throw new EntityNotFoundException(employee.getEmployeeId().getId());
        }
        return EmployeePojoMapper.map(employeeDao.save(EmployeePojoMapper.map(employee)));
    }
    @TimeAndLogg
    public List<Employee> getAllEmployees() {
        return EmployeePojoMapper.map(employeeDao.findAll());
    }
}
