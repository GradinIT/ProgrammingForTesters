package se.jocke.employee.api.builder;


import se.jocke.api.EmployeeModel;
import se.jocke.department.entity.EmployeeID;
import se.jocke.department.entity.EntityID;

import java.math.BigDecimal;

public class EmployeeModelTestBuilder {

    public static EmployeeModel.EmployeeModelBuilder builder() {
        return EmployeeModel.builder()
                .employeeId(100)
                .firstName("Matthaios")
                .lastName("Tasios")
                .salary(BigDecimal.valueOf(25000.0))
                .fullTime(true)
                .departmentId(1);
    }
}