package se.jocke.employee.Builder;

import se.jocke.employee.entity.Employee;
import se.jocke.employee.entity.EmployeeID;

import java.math.BigDecimal;

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
