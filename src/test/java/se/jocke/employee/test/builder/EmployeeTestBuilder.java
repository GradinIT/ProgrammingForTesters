package se.jocke.employee.test.builder;

import se.jocke.employee.entity.Employee;
import se.jocke.employee.entity.EmployeeID;


import java.math.BigDecimal;

public class EmployeeTestBuilder {
    public static Employee.EmployeeBuilder builder() {
        return Employee.builder()
                .firstName("Jocke")
                .lastName("Gradin")
                .fullTime(false)
                .departmentId(400)
                .salary(new BigDecimal(16000))
                .employeeId(EmployeeID.builder().id(7).build());
    }
}
