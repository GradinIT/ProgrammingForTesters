package se.jocke.employee.builder;

import se.jocke.employee.entity.Employee;
import se.jocke.employee.entity.EmployeeID;

import java.math.BigDecimal;

public class EmployeeTestBuilder {

    public static Employee.EmployeeBuilder builder() {
        return Employee.builder()
                .employeeId(EmployeeTestFixture.employeeId)
                .firstName(EmployeeTestFixture.firstName)
                .lastName(EmployeeTestFixture.lastName)
                .departmentId(EmployeeTestFixture.departmentId)
                .salary(EmployeeTestFixture.salary)
                .fullTime(EmployeeTestFixture.fullTime);
        
    }
}

