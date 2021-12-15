package se.jocke.employee.unittests.Builder;

import se.jocke.employee.unittests.entity.Employee;

public class EmployeeTestBuilderTwo {
    public static Employee build() {
        return   Employee.builder()
                .employeeId(EmployeeBuilderFixtureTwo.EMPLOYEE_ID)
                .firstName(EmployeeBuilderFixtureTwo.FIRSTNAME)
                .lastName(EmployeeBuilderFixtureTwo.LASTNAME)
                .salary(EmployeeBuilderFixtureTwo.SALARY)
                .fullTime(EmployeeBuilderFixtureTwo.FULLTIME)
                .departmentId(EmployeeBuilderFixtureTwo.DEPARTMENTID)
                .build();

    }
}