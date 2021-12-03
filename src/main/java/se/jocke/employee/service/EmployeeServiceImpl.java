package se.jocke.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.jocke.common.aspects.TimeAndLogg;
import se.jocke.employee.dao.EmployeeDao;
import se.jocke.employee.dao.EmployeeDatabaseEntry;
import se.jocke.employee.dao.EmployeeDatabaseEntryMapper;
import se.jocke.employee.entity.Employee;
import se.jocke.common.dao.EntityAlreadyInStorageException;
import se.jocke.common.dao.EntityNotFoundException;

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
            return EmployeeDatabaseEntryMapper.map(employee.get());
        else throw new EntityNotFoundException(employeeId);
    }

    @TimeAndLogg
    public Employee createEmployee(Employee employee) {
        Optional<EmployeeDatabaseEntry> e = employeeDao.findById(employee.getEmployeeId().getId());
        if(e.isPresent()) {
            throw new EntityAlreadyInStorageException(employee.getEmployeeId().getId());
        }
        return EmployeeDatabaseEntryMapper.map(employeeDao.save(EmployeeDatabaseEntryMapper.map(employee)));
    }


    @TimeAndLogg
    public Employee removeEmployee(Employee employee) {
        Optional<EmployeeDatabaseEntry> e = employeeDao.findById(employee.getEmployeeId().getId());
        if(!e.isPresent()) {
            throw new EntityNotFoundException(employee.getEmployeeId().getId());
        }
        employeeDao.delete(EmployeeDatabaseEntryMapper.map(employee));
        return employee;
    }
    @TimeAndLogg
    public Employee updateEmployee(Employee employee) {
        Optional<EmployeeDatabaseEntry> e = employeeDao.findById(employee.getEmployeeId().getId());
        if(!e.isPresent()) {
            throw new EntityNotFoundException(employee.getEmployeeId().getId());
        }
        return EmployeeDatabaseEntryMapper.map(employeeDao.save(EmployeeDatabaseEntryMapper.map(employee)));
    }
    @TimeAndLogg
    public List<Employee> getAllEmployees() {
        return EmployeeDatabaseEntryMapper.map(employeeDao.findAll());
    }
}
