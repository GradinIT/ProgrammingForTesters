package se.jocke.employee.Builder;

import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.entity.EmployeeID;

import java.math.BigDecimal;

public class EmployeeModelTestBuilder {
    public static EmployeeModel.EmployeeModelBuilder builder() {
        return EmployeeModel.builder()
                .firstName("Runar")
                .lastName("Sopranos")
                .employeeId(3)
                .salary(new BigDecimal("22500"))
                .fullTime(Boolean.FALSE)
                .departmentId(1);
    }
}
