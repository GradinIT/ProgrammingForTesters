package se.jensen.dao.mapper;

import se.jensen.dao.EmployeePOJO;
import se.jensen.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeePojoMapper {
    public static Employee map (EmployeePOJO employeePOJO) {
        return Employee.builder()
                .setEmployeeId(employeePOJO.getEmployeeId())
                .setFirstName(employeePOJO.getFirstName())
                .setLastName(employeePOJO.getFirstName())
                .setFullTime(employeePOJO.getFullTime())
                .setSalary(employeePOJO.getSalary())
                .build();
    }
    public static EmployeePOJO map (Employee employee) {
        EmployeePOJO employeePOJO = new EmployeePOJO();
        employeePOJO.setEmployeeId(employee.getEmployeeId());
        employeePOJO.setFirstName(employee.getFirstName());
        employeePOJO.setLastName(employee.getLastName());
        employeePOJO.setFullTime(employee.getFullTime());
        employeePOJO.setSalary(employee.getSalary());
        return  employeePOJO;
    }

    public static List<Employee> map(List<EmployeePOJO> all) {
        List<Employee> employees = new ArrayList<>();
        for(EmployeePOJO employeePOJO: all)
            employees.add(map(employeePOJO));
        return employees;
    }
}
