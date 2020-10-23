package se.jensen.dao;

import com.google.common.collect.Maps;
import se.jensen.entity.Employee;
import se.jensen.entity.EmployeeTestBuilder;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

public class EmployeeFakeDao implements EmployeeDao {
    private final Map<Integer, Employee> storage = Maps.newHashMap();

    public Employee getEmployee(Integer employeeId) throws EntityNotFoundException {
        if (storage.containsKey(employeeId))
            return storage.get(employeeId);
        throw new EntityNotFoundException(employeeId);
    }

    public Collection<Employee> getAllEmployees() {
        return storage.values();
    }

    public void setTestData() {
        storage.put(1, EmployeeTestBuilder.builder()
                .setEmployeeId(1)
                .setFirstName("One")
                .setLastName("Onesson")
                .setSalary(BigDecimal.valueOf(22000))
                .setFullTime(Boolean.TRUE)
                .build());
        storage.put(2, EmployeeTestBuilder.builder()
                .setEmployeeId(2)
                .setFirstName("Two")
                .setLastName("Twosson")
                .setSalary(BigDecimal.valueOf(22000))
                .setFullTime(Boolean.TRUE)
                .build());
    }
}
