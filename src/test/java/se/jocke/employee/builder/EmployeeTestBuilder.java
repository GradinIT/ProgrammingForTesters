package se.jocke.employee.builder;

import se.jocke.employee.entity.Employee;
import se.jocke.employee.entity.EmployeeID;

import java.math.BigDecimal;

public class EmployeeTestBuilder {

    public static Employee.EmployeeBuilder builder() {
        return Employee.builder()
                .employeeId(EmployeeID.builder().id(2).build())
                .firstName("Test/QA")
                .lastName("SQUARE1")
                .departmentId(5)
                .salary(new BigDecimal(50000))
                .fullTime(true);
        
    }
}

