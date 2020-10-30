package se.jensen.dao;

import se.jensen.entity.Employee;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.toList;

public class EmployeeFakeDao implements EmployeeDao {
    private final Map<Integer, Employee> storage;

    public EmployeeFakeDao(Map<Integer, Employee> storage) {
        this.storage = Objects.requireNonNull(storage);
    }

    public Employee getEmployee(Integer employeeId) {
        if (storage.containsKey(employeeId))
            return storage.get(employeeId);
        throw new EntityNotFoundException(employeeId);
    }

    public List<Employee> getAllEmployees() {
        return storage.values().stream().collect(toList());
    }

    @Override
    public Employee create(Employee employee) {
        if(storage.containsKey(employee.getEmployeeId()))
            throw new EntityAlreadyInStorageException(employee);
        storage.put(employee.getEmployeeId(),employee);
        return getEmployee(employee.getEmployeeId());
    }

    @Override
    public Employee delete(Employee employee) {
        if (!storage.containsKey(employee.getEmployeeId()))
            throw new EntityNotFoundException(employee.getEmployeeId());
        return storage.remove(employee.getEmployeeId());
    }

    @Override
    public Employee update(Employee employee) {
        if (!storage.containsKey(employee.getEmployeeId()))
            throw new EntityNotFoundException(employee.getEmployeeId());
        return storage.replace(employee.getEmployeeId(),employee);
    }

    @Override
    public Employee updateOrCreate(Employee employee) {
        if (!storage.containsKey(employee.getEmployeeId()))
            return create(employee);
        return update(employee);
    }

    public void setTestData() {
        storage.put(1, Employee.builder()
                .setEmployeeId(1)
                .setFirstName("One")
                .setLastName("Onesson")
                .setSalary(BigDecimal.valueOf(22000))
                .setFullTime(Boolean.TRUE)
                .build());
        storage.put(2, Employee.builder()
                .setEmployeeId(2)
                .setFirstName("Two")
                .setLastName("Twosson")
                .setSalary(BigDecimal.valueOf(22000))
                .setFullTime(Boolean.TRUE)
                .build());
        storage.put(3, Employee.builder()
                .setEmployeeId(3)
                .setFirstName("Runar")
                .setLastName("Sörgaard")
                .setSalary(BigDecimal.valueOf(1000000))
                .setFullTime(Boolean.FALSE)
                .build());
        storage.put(4, Employee.builder()
                .setEmployeeId(4)
                .setFirstName("Carola")
                .setLastName("Häggkvist")
                .setSalary(BigDecimal.valueOf(90))
                .setFullTime(Boolean.FALSE)
                .build());
    }
}
