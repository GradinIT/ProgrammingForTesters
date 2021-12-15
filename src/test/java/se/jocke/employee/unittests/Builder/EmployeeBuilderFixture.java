package se.jocke.employee.unittests.Builder;

import lombok.experimental.SuperBuilder;
import se.jocke.employee.unittests.entity.EmployeeID;

import java.math.BigDecimal;
@SuperBuilder
public class EmployeeBuilderFixture {
    public static final String FIRSTNAME = "Runar";
    public static final String LASTNAME = "Sopranos";
    public static final Integer DEPARTMENTID = 1;
    public static final BigDecimal SALARY = new BigDecimal(22500);
    public static final Boolean FULLTIME = Boolean.FALSE;
    public static final EmployeeID EMPLOYEE_ID = EmployeeID.builder().id(86).build();

}
