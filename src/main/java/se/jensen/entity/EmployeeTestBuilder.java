package se.jensen.entity;

import java.math.BigDecimal;

public class EmployeeTestBuilder {
    private static Employee.Builder builder = Employee.builder();

    public static Employee build() {
        return builder
                .setEmployeeId(1)
                .setFirstName("Testare")
                .setLastName("Testarsson")
                .setSalary(BigDecimal.valueOf(25000.0))
                .setFullTime(Boolean.TRUE)
                .build();
    }


    public static Employee.Builder builder() {
        return builder;
    }
}
