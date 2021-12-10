package se.jocke.employee.unittests.Builder;

import se.jocke.employee.unittests.entity.Employee;

public class EmployeeTestBuilder {
    public static Employee build() {
        return   Employee.builder()
                .employeeId(EmployeeBuilderFixture.EMPLOYEE_ID)
                .firstName(EmployeeBuilderFixture.FIRSTNAME)
                .lastName(EmployeeBuilderFixture.LASTNAME)
                .salary(EmployeeBuilderFixture.SALARY)
                .fullTime(EmployeeBuilderFixture.FULLTIME)
                .departmentId(EmployeeBuilderFixture.DEPARTMENTID)
                .build();

    }
}
