package se.jocke.employee.builder;

import org.junit.jupiter.api.Test;
import se.jocke.api.EmployeeModel;

import java.math.BigDecimal;

public class EmployeeModelTestBuilder {

    @Test
    public static EmployeeModel.EmployeeModelBuilder builder() {
        return EmployeeModel.builder()
                .employeeId(200)
                .departmentId(1)
                .firstName("Anders")
                .lastName("Andersson")
                .fullTime(Boolean.TRUE)
                .salary(BigDecimal.valueOf(25000));
    }
}
