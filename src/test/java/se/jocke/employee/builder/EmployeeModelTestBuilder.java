package se.jocke.employee.builder;

import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.entity.EmployeeID;

import java.math.BigDecimal;

public class EmployeeModelTestBuilder {
    public static final EmployeeID EMPLOYEE_ID = EmployeeID.builder().id(1).build();

    // Same as employee id 1 in employee-changelog.xml
    public static EmployeeModel.EmployeeModelBuilder builder() {
        return EmployeeModel.builder()
                .employeeId(EMPLOYEE_ID.getId())
                .firstName("firstName1")
                .lastName("LastName1")
                .salary(new BigDecimal(25000))
                .fullTime(true)
                .departmentId(1);
    }
}