package se.jocke.employee.builder;

import se.jocke.department.entity.Employee;
import se.jocke.department.entity.EmployeeID;

import java.math.BigDecimal;

public class EmployeeTestBuilder {
    // EmployeeBuilder är en grej som finns gömt i @Superbuilder i Employee
    private static Employee.EmployeeBuilder builder = Employee.builder();

    public static Employee.EmployeeBuilder builderMethod() {
        return builder
                .employeeId(EmployeeID.builder().id(100).build())
                .firstName("Testare")
                .lastName("Testarsson")
                .salary(BigDecimal.valueOf(25000))
                .fullTime(Boolean.TRUE)
                .departmentId(1);
    }
}
