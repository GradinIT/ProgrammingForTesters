package se.jensen.dao.mapper;

import se.jensen.dao.EmployeeDatabaseEntry;
import se.jensen.entity.Employee;
import se.jensen.entity.EmployeeID;

import java.util.ArrayList;
import java.util.List;

public class EmployeePojoMapper {
    public static Employee map(EmployeeDatabaseEntry employeeDatabaseEntry) {
        return Employee.builder()
                .employeeId(EmployeeID.builder().id(employeeDatabaseEntry.getEmployeeId()).build())
                .firstName(employeeDatabaseEntry.getFirstName())
                .lastName(employeeDatabaseEntry.getLastName())
                .fullTime(employeeDatabaseEntry.getFullTime())
                .salary(employeeDatabaseEntry.getSalary())
                .departmentId(employeeDatabaseEntry.getDepartmentId())
                .build();
    }

    public static EmployeeDatabaseEntry map(Employee employee) {
        return EmployeeDatabaseEntry.builder()
                .employeeId(employee.getEmployeeId().getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .fullTime(employee.getFullTime())
                .salary(employee.getSalary())
                .departmentId(employee.getDepartmentId())
                .build();
    }

    public static List<Employee> map(List<EmployeeDatabaseEntry> all) {
        List<Employee> employees = new ArrayList<>();
        for (EmployeeDatabaseEntry employeeDatabaseEntry : all)
            employees.add(map(employeeDatabaseEntry));
        return employees;
    }
}
