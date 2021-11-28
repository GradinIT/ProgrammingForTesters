package se.jocke.employee.entity;

import java.math.BigDecimal;

public class EmployeeTestFixture {
    public static final String name = "HEJ";
    public static final EmployeeID id = EmployeeID.builder().id(1).build();  // varför är den här en builder?
    public static final String firstName = "Daniel";
    public static final String lastName = "Hock";
    public static final BigDecimal salary = new BigDecimal("10000");
    public static final Boolean fullTime = true;
    public static final Integer departmentId = 5;

    // E.T.Fixture == Ett känt läge för all data som används i testfallen. förbestämt, statiskt

}
