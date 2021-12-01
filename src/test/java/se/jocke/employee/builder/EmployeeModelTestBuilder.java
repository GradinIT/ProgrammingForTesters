package se.jocke.employee.builder;


import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.entity.EmployeeID;

import java.math.BigDecimal;

public class EmployeeModelTestBuilder {
    public static EmployeeModel.EmployeeModelBuilder builder() {
        return EmployeeModel.builder()
                .employeeId(EmployeeTestFixture.employeeId.getId())
                .firstName(EmployeeTestFixture.firstName)
                .lastName(EmployeeTestFixture.lastName)
                .departmentId(EmployeeTestFixture.departmentId)
                .salary(EmployeeTestFixture.salary)
                .fullTime(EmployeeTestFixture.fullTime);



    }
}

