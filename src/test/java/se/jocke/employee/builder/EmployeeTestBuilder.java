package se.jocke.employee.builder;

import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.entity.EmployeeID;


public class EmployeeTestBuilder {
    private static final EmployeeModel ACTUAL_EMPLOYEE = EmployeeModelTestBuilder.builder().build();
    public static final EmployeeID EMPLOYEE_ID = EmployeeID.builder().id(ACTUAL_EMPLOYEE.getEmployeeId()).build();

    public static Employee.EmployeeBuilder builder() {
        return Employee.builder()
                .employeeId(EMPLOYEE_ID)
                .firstName(ACTUAL_EMPLOYEE.getFirstName())
                .lastName(ACTUAL_EMPLOYEE.getLastName())
                .salary(ACTUAL_EMPLOYEE.getSalary())
                .fullTime(ACTUAL_EMPLOYEE.getFullTime())
                .departmentId(ACTUAL_EMPLOYEE.getDepartmentId());
    }
}