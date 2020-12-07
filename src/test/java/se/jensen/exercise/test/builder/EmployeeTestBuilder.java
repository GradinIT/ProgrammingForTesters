package se.jensen.exercise.test.builder;

import se.jensen.entity.Employee;
import se.jensen.entity.EmployeeID;

import java.math.BigDecimal;

public class EmployeeTestBuilder {
    private static Employee.EmployeeBuilder builder = Employee.builder();

    public static Employee build() {
        return builder
                .employeeId(EmployeeID.builder().id(1).build())
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
