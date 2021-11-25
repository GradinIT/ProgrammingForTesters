package se.jocke.employee.builder;

import io.cucumber.java.sl.In;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.entity.EmployeeID;

import java.math.BigDecimal;

public class EmployeeTestBuilder {


    public static Employee builder() {
        return Employee.builder()
                .employeeId(EmployeeID.builder().id(2).build())
                .firstName("Test")
                .lastName("Tester")
                .salary(new BigDecimal(0))
                .fullTime(true)
                .departmentId(1)
                .build();
    }
}
