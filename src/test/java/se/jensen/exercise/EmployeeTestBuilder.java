package se.jensen.exercise;

import se.jensen.entity.Employee;

import java.math.BigDecimal;

public class EmployeeTestBuilder {

    private static Employee.EmployeeBuilder builder = Employee.builder();

    public static Employee build() {
        return builder
                .employeeId(1)
                .firstName("Testare")
                .lastName("Testarsson")
                .salary(BigDecimal.valueOf(25000.0))
                .fullTime(Boolean.TRUE)
                .departmentId(1)
                .build();
    }


    public static Employee.EmployeeBuilder builder() {
        return builder;
    }
}
