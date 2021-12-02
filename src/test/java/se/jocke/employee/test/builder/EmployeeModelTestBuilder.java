package se.jocke.employee.test.builder;

import se.jocke.employee.api.EmployeeModel;

public class EmployeeModelTestBuilder {
    public static EmployeeModel build() {
        return EmployeeModel.builder()
                .employeeId(EmployeeTestFixture.id.getId())
                .firstName(EmployeeTestFixture.firstName)
                .lastName(EmployeeTestFixture.lastName)
                .salary(EmployeeTestFixture.salary)
                .fullTime(EmployeeTestFixture.fulltime)
                .departmentId(EmployeeTestFixture.departmentId)
                .build();

    }
}
