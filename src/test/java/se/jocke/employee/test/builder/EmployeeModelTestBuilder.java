package se.jocke.employee.test.builder;

import se.jocke.employee.api.EmployeeModel;

import java.math.BigDecimal;

public class EmployeeModelTestBuilder {
    public static EmployeeModel bygg(){
        return EmployeeModel.builder()
                .employeeId(EmployeeTestFixture.employeeID)
                .firstName(EmployeeTestFixture.firstName)
                .lastName(EmployeeTestFixture.lastName)
                .salary(new BigDecimal(EmployeeTestFixture.salary))
                .fullTime(EmployeeTestFixture.fullTime)
                .departmentId(EmployeeTestFixture.departmentID)
                .build();

    }
}
