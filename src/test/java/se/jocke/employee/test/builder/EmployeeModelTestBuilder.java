package se.jocke.employee.test.builder;

import se.jocke.employee.old.api.EmployeeModel;

import java.math.BigDecimal;

public class EmployeeModelTestBuilder {
    public static EmployeeModel.EmployeeModelBuilder builder() {
        return EmployeeModel.builder()
                .firstName("Jocke")
                .lastName("Gradin")
                .fullTime(false)
                .departmentId(400)
                .salary(new BigDecimal(16000))
                .employeeId(7);
    }
}
