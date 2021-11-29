package se.jocke.employee.builder.java;

import se.jocke.department.entity.Department;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.entity.EmployeeID;

import java.math.BigDecimal;

public class EmployeeTestBuilder {
    private static Object BigDecimal;

    public static Employee.EmployeeBuilder builder() {
        return Employee.builder()
                .employeeId(EmployeeID.builder().id(2).build())
                .firstName("Priyanka")
                .lastName("Tuteja")
                .salary(new BigDecimal(20000))
                .fullTime(Boolean.TRUE)
                .departmentId(5);

    }

}


