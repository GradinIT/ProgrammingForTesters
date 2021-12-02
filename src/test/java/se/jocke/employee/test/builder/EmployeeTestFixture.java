package se.jocke.employee.test.builder;

import se.jocke.employee.unittest.entity.EmployeeID;

import java.math.BigDecimal;
//ska egentligen ligga employee>builder

public class EmployeeTestFixture {
    public static final EmployeeID id = EmployeeID.builder().id(3).build(); // ändra till EmployeeID istället för Integer
    public static String firstName = "Daniel";
    public static String lastName = "Hock";
    public static BigDecimal salary = new BigDecimal("10000");
    public static Boolean fullTime = true;
    public static Integer departmentId = 5;

  //  public static final Integer id = 1;
 //   .employeeId(employee.getEmployeeId().getId())
}
