package se.jocke.employee.testbuilder;

import se.jocke.employee.api.EmployeeModel;

import se.jocke.employee.entity.EmployeeID;
import se.jocke.employee.entity.EmployeeTestFixture;

public class EmployeeModelTestBuilder {
    public static EmployeeModel build () {
        return EmployeeModel.builder()
               .employeeId(EmployeeTestFixture.id.getId())  // varför är det en builder? måste ha med .getId()
                .firstName(EmployeeTestFixture.firstName)
                .lastName(EmployeeTestFixture.lastName)
                .salary(EmployeeTestFixture.salary)
                .fullTime(EmployeeTestFixture.fullTime)
                .departmentId(EmployeeTestFixture.departmentId)
                .build();

    }
}
