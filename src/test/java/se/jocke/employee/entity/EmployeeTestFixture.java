package se.jocke.employee.entity;

import java.math.BigDecimal;

public class EmployeeTestFixture {
    public static final String name = "HEJ";
    public static final EmployeeID id = EmployeeID.builder().id(3).build(); // ändra till EmployeeID istället för Integer
    public static String firstName = "Daniel";
    public static String lastName = "Hock";
    public static BigDecimal salary = new BigDecimal("10000");
    public static Boolean fullTime = true;
    public static Integer departmentId = 5;

  //  public static final Integer id = 1;
 //   .employeeId(employee.getEmployeeId().getId())
}
