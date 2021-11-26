package se.jocke.employee.Builder;

import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.entity.EmployeeID;

import java.math.BigDecimal;

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