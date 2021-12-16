package se.jocke.employee.unittests.Builder;

import se.jocke.employee.api.EmployeeModel;

public class EmployeeModelTestBuilder {
    public static EmployeeModel build() {
        return EmployeeModel.builder()
                .employeeId(EmployeeBuilderFixture.EMPLOYEE_ID.getId())
                .firstName(EmployeeBuilderFixture.FIRSTNAME)
                .lastName(EmployeeBuilderFixture.LASTNAME)
                .salary(EmployeeBuilderFixture.SALARY)
                .fullTime(EmployeeBuilderFixture.FULLTIME)
                .departmentId(EmployeeBuilderFixture.DEPARTMENTID)
                .build();
    }
}