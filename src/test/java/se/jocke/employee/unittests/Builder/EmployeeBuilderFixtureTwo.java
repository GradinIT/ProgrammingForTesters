package se.jocke.employee.unittests.Builder;

import lombok.experimental.SuperBuilder;
import se.jocke.employee.unittests.entity.EmployeeID;

import java.math.BigDecimal;
@SuperBuilder
public class EmployeeBuilderFixtureTwo
 {
    public static final String FIRSTNAME = "Carola";
    public static final String LASTNAME = "Corleone";
    public static final Integer DEPARTMENTID = 1;
    public static final BigDecimal SALARY = new BigDecimal(23500);
    public static final Boolean FULLTIME = Boolean.FALSE;
    public static final EmployeeID EMPLOYEE_ID = EmployeeID.builder().id(100).build();

}

