package se.jocke.employee.builder;

import se.jocke.api.EmployeeModel;

import java.math.BigDecimal;

public class EmployeeModelTestBuilder {
    public static EmployeeModel.EmployeeModelBuilder builder() {
        return EmployeeModel.builder()
                .employeeId(100)
                .departmentId(1)
                .firstName("Johan")
                .lastName("Johansson")
                .fullTime(Boolean.TRUE)
                .salary(BigDecimal.valueOf(25000));
    }
}
