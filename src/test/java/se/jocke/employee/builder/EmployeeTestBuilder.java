package se.jocke.employee.builder;

import se.jocke.department.entity.Employee;
import se.jocke.department.entity.EmployeeID;

import java.math.BigDecimal;

public class EmployeeTestBuilder {
    private static Employee.EmployeeBuilder builder = Employee.builder();

    public static Employee.EmployeeBuilder builder() {
        return builder
                .employeeId(EmployeeID.builder().id(100).build())
                .firstName("Max")
                .lastName("Martin")
                .salary(BigDecimal.valueOf(25000.0))
                .fullTime(Boolean.TRUE)
                .departmentId(1);
    }
}
