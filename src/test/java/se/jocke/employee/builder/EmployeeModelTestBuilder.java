package se.jocke.employee.builder;


import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.entity.EmployeeID;

import java.math.BigDecimal;

public class EmployeeModelTestBuilder {
    public static EmployeeModel.EmployeeModelBuilder builder() {
        return EmployeeModel.builder()
                .employeeId(EmployeeID.builder().id(2).build().getId())
                .firstName("Test/QA")
                .lastName("SQUARE1")
                .departmentId(5)
                .salary(new BigDecimal(50000))
                .fullTime(true);



    }
}

