package se.jocke.employee.unittest.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import se.jocke.employee.entity.Employee;
import se.jocke.employee.test.builder.EmployeeTestBuilder;

public class TestEmployee {

    private static final Employee EMPLOYEE = EmployeeTestBuilder.build();

    @Test
    public void testThatDepartmentIsCreated () {
        Employee employee = Employee.builder()
                .departmentId(EMPLOYEE.getDepartmentId())
                .employeeId(EMPLOYEE.getEmployeeId())
                .fullTime(EMPLOYEE.getFullTime())
                .firstName(EMPLOYEE.getFirstName())
                .lastName(EMPLOYEE.getLastName())
                .salary(EMPLOYEE.getSalary())
                .build();

        Assertions.assertEquals(EMPLOYEE,employee);
        Assertions.assertEquals(EMPLOYEE.getFirstName(), employee.getFirstName());
        Assertions.assertEquals(EMPLOYEE.getDepartmentId(), employee.getDepartmentId());
        String EMPLOYEE_TO_STRING_VALUE = EMPLOYEE.toString();
        String employee_to_string_value = employee.toString();
        Assertions.assertEquals(EMPLOYEE_TO_STRING_VALUE,employee_to_string_value);


    }
    @Test
    public void testThatNullPointerExceptionIsRaisedWhenOnlyProvidingDepartmentId() {
        Assertions.assertThrows(NullPointerException.class ,
                () -> Employee.builder().departmentId(EMPLOYEE.getDepartmentId()).build());

    }
    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingEmployeeLastName() {
        Assertions.assertThrows(NullPointerException.class ,
                () -> Employee.builder().employeeId(EMPLOYEE.getEmployeeId())
                        .departmentId(EMPLOYEE.getDepartmentId())
                        .firstName(EMPLOYEE.getFirstName())
                        .fullTime(EMPLOYEE.getFullTime())
                        .salary(EMPLOYEE.getSalary())
                      //  .lastName(EMPLOYEE.getLastName()) - testar att denna inte är med
                        .build());

    }
}

