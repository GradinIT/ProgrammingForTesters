package se.jocke.employee.unittests.Builder;

import se.jocke.employee.unittests.api.EmployeeModel;

public class EmployeeModelTestBuilderTwo {
    public static EmployeeModel build() {
        return EmployeeModel.builder()
                .employeeId(EmployeeBuilderFixtureTwo.EMPLOYEE_ID.getId())
                .firstName(EmployeeBuilderFixtureTwo.FIRSTNAME)
                .lastName(EmployeeBuilderFixtureTwo.LASTNAME)
                .salary(EmployeeBuilderFixtureTwo.SALARY)
                .fullTime(EmployeeBuilderFixtureTwo.FULLTIME)
                .departmentId(EmployeeBuilderFixtureTwo.DEPARTMENTID)
                .build();
    }
}
