package se.jocke.employee.Builder;

import lombok.experimental.SuperBuilder;
import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.entity.EmployeeID;

import java.math.BigDecimal;
@SuperBuilder
public class EmployeeBuilderFixture {
    public static final String FIRSTNAME = "Runar";
    public static final String LASTNAME = "Sopranos";
    public static final Integer DEPARTMENTID = 1;
    public static final BigDecimal SALARY = new BigDecimal(22500);
    public static final Boolean FULLTIME = Boolean.FALSE;
    public static final EmployeeID EMPLOYEE_ID = EmployeeID.builder().id(2).build();
    public static final Integer EMPLOYEE_ID_INTEGER = 1;
}
