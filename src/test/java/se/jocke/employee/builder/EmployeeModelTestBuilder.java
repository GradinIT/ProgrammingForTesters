package se.jocke.employee.builder;

import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.entity.EmployeeID;

import java.math.BigDecimal;

public class EmployeeModelTestBuilder {
    public static final EmployeeID EMPLOYEE_ID = EmployeeID.builder().id(2).build();

    public static EmployeeModel.EmployeeModelBuilder builder() {
        return EmployeeModel.builder()
                .employeeId(EMPLOYEE_ID.getId())
                .firstName("Monty")
                .lastName("Python")
                .salary(new BigDecimal(20))
                .fullTime(true)
                .departmentId(100);
    }
}