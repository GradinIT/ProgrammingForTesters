package se.jocke.employee.builder;

import se.jocke.employee.entity.Employee;
import se.jocke.employee.entity.EmployeeID;

import java.math.BigDecimal;

public class EmployeeTestBuilder {


    public static Employee builder() {
        return Employee.builder()
                .employeeId(EmployeeID.builder().id(2).build())
                .firstName("Monthy")
                .lastName("Python")
                .salary(new BigDecimal(10))
                .fullTime(true)
                .departmentId(1)
                .build();
    }
}
