package se.jocke.employee.test.builder;

import se.jocke.employee.api.EmployeeModel;

import java.math.BigDecimal;

public class EmployeeModelTestBuilder {
    public static EmployeeModel.EmployeeModelBuilder builder() {
        return EmployeeModel.builder()
                .firstName("Micke")
                .lastName("Tolfstrom")
                .fullTime(true)
                .departmentId(500)
                .salary(new BigDecimal(12000))
                .employeeId(800);
    }
}
