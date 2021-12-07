package se.jocke.employee.Builder;

import se.jocke.employee.api.EmployeeModel;

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
