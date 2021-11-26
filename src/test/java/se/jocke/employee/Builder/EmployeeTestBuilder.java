package se.jocke.employee.Builder;

import se.jocke.employee.entity.Employee;
import se.jocke.employee.entity.EmployeeID;

import java.math.BigDecimal;

public class EmployeeTestBuilder {
    public static Employee.EmployeeBuilder builder() {
        return Employee.builder().employeeId(EmployeeID.builder().id(2).build())
                .firstName("Joakim")
                .lastName("Gradin")
                .salary(new BigDecimal("22000"))
                .fullTime(Boolean.TRUE)
                .departmentId(2);

    }
}
