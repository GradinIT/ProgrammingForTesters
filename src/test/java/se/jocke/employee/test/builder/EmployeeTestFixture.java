package se.jocke.employee.test.builder;

import liquibase.pro.packaged.B;
import se.jocke.employee.entity.EmployeeID;

import java.math.BigDecimal;

public class EmployeeTestFixture {

    public static final EmployeeID id = EmployeeID.builder().id(2).build();
    public static String firstName = "Fagel";
    public static String lastName = "Holk";
    public static BigDecimal salary = new BigDecimal("222222");
    public static Boolean fullTime = true;
    public static Integer departmentId = 55;



}
