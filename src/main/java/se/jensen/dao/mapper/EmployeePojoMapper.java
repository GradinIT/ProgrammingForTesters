package se.jensen.dao.mapper;

import se.jensen.dao.EmployeeDataBaseEntity;
import se.jensen.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeePojoMapper {
    public static Employee map (EmployeeDataBaseEntity employeeDataBaseEntity) {
        return Employee.builder()
                .setEmployeeId(employeeDataBaseEntity.getEmployeeId())
                .setFirstName(employeeDataBaseEntity.getFirstName())
                .setLastName(employeeDataBaseEntity.getFirstName())
                .setFullTime(employeeDataBaseEntity.getFullTime())
                .setSalary(employeeDataBaseEntity.getSalary())
                .build();
    }
    public static EmployeeDataBaseEntity map (Employee employee) {
        EmployeeDataBaseEntity employeeDataBaseEntity = new EmployeeDataBaseEntity();
        employeeDataBaseEntity.setEmployeeId(employee.getEmployeeId());
        employeeDataBaseEntity.setFirstName(employee.getFirstName());
        employeeDataBaseEntity.setLastName(employee.getLastName());
        employeeDataBaseEntity.setFullTime(employee.getFullTime());
        employeeDataBaseEntity.setSalary(employee.getSalary());
        return employeeDataBaseEntity;
    }

    public static List<Employee> map(List<EmployeeDataBaseEntity> all) {
        List<Employee> employees = new ArrayList<>();
        for(EmployeeDataBaseEntity employeeDataBaseEntity : all)
            employees.add(map(employeeDataBaseEntity));
        return employees;
    }
}
