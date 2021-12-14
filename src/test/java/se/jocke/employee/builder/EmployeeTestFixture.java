package se.jocke.employee.builder;

import se.jocke.employee.entity.EmployeeID;

import java.math.BigDecimal;

public class EmployeeTestFixture {
    public static final EmployeeID employeeId = EmployeeID.builder().id(7).build();
    public static final String firstName = "Test/QA";
    public static final String lastName = "SQUARE1";
    public static final BigDecimal salary = new BigDecimal(50000);
    public static final Boolean fullTime= true;
    public static final Integer departmentId= 5;


}
