package se.jensen.dao.mapper;

import se.jensen.dao.EmployeePOJO;
import se.jensen.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeePojoMapper {
    public static Employee map (EmployeePOJO employeePOJO) {
        return Employee.builder()
                .employeeId(employeePOJO.getEmployeeId())
                .firstName(employeePOJO.getFirstName())
                .lastName(employeePOJO.getFirstName())
                .fullTime(employeePOJO.getFullTime())
                .salary(employeePOJO.getSalary())
                .build();
    }
    public static EmployeePOJO map (Employee employee) {
        return EmployeePOJO.builder()
                .employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .fullTime(employee.getFullTime())
                .salary(employee.getSalary())
                .build();
    }

    public static List<Employee> map(List<EmployeePOJO> all) {
        List<Employee> employees = new ArrayList<>();
        for(EmployeePOJO employeePOJO: all)
            employees.add(map(employeePOJO));
        return employees;
    }
}
