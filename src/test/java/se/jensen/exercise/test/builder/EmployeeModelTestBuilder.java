package se.jensen.exercise.test.builder;

import se.jensen.api.EmployeeModel;

import java.math.BigDecimal;

public class EmployeeModelTestBuilder {
    public static EmployeeModel build() {
        return EmployeeModel.builder()
                .employeeId(1)
                .departmentId(1)
                .firstName("Anders")
                .lastName("Andersson")
                .fullTime(Boolean.TRUE)
                .salary(BigDecimal.valueOf(25000))
                .build();
    }
}
