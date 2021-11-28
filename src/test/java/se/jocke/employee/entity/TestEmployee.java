package se.jocke.employee.entity;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import se.jocke.employee.builder.EmployeeTestBuilder;


public class TestEmployee {
    private static final Employee EMPLOYEE = EmployeeTestBuilder.build();

    @Test
    public void testThatEmployeeIsCreated(){

        Employee employee = Employee.builder()
                .firstName(EMPLOYEE.getFirstName())
                .lastName(EMPLOYEE.getLastName())
                .fullTime(EMPLOYEE.getFullTime())
                .salary(EMPLOYEE.getSalary())
                .departmentId(EMPLOYEE.getDepartmentId())
                .employeeId(EMPLOYEE.getEmployeeId())
                .build();

        Assertions.assertEquals(EMPLOYEE,employee);
        Assertions.assertEquals(EMPLOYEE.getEmployeeId(), employee.getEmployeeId());
        Assertions.assertEquals(EMPLOYEE.getDepartmentId(), employee.getDepartmentId());
        String EMPLOYEE_TO_STRING_VALUE = EMPLOYEE.toString();
        String employee_to_string_value = employee.toString();
        Assertions.assertEquals(EMPLOYEE_TO_STRING_VALUE,employee_to_string_value);
    }

    @Test
    public void testthatNullcheckworks2 () {
        Assertions.assertThrows(NullPointerException.class , () -> Employee.builder()
                .firstName(EMPLOYEE.getFirstName())
                .lastName(EMPLOYEE.getLastName())
                .fullTime(EMPLOYEE.getFullTime())
                .salary(EMPLOYEE.getSalary())
                .departmentId(EMPLOYEE.getDepartmentId())
                .build());
    }

    @Test
    public void testThatNullPointerExceptionIsRaisedWhenOnlyprovidingDepartmentId() {
        Assertions.assertThrows(NullPointerException.class ,
                () -> Employee.builder().departmentId(EMPLOYEE.getDepartmentId()).build());

    }

    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingDepartmentId() {
        Assertions.assertThrows(NullPointerException.class ,
                () -> Employee.builder().firstName(EMPLOYEE.getFirstName())
                        .lastName(EMPLOYEE.getLastName())
                        .salary(EMPLOYEE.getSalary())
                        .fullTime(EMPLOYEE.getFullTime())
                   //   .departmentId(EMPLOYEE.getDepartmentId())
                        .employeeId(EMPLOYEE.getEmployeeId())
                        .build());

    }
}
