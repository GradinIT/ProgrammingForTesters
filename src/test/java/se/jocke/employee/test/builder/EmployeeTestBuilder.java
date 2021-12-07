package se.jocke.employee.test.builder;

import se.jocke.employee.entity.Employee;
import se.jocke.employee.entity.EmployeeID;

import java.math.BigDecimal;

public class EmployeeTestBuilder {
    public static Employee bygg(){
        return Employee.builder()
                .departmentId(EmployeeTestFixture.departmentID)
                .employeeId(EmployeeID.builder().id(EmployeeTestFixture.employeeID).build())
                .firstName(EmployeeTestFixture.firstName)
                .lastName(EmployeeTestFixture.lastName)
                .fullTime(EmployeeTestFixture.fullTime)
                .salary(new BigDecimal(EmployeeTestFixture.salary))
                .build();
    }
}
