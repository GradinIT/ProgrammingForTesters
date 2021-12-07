package se.jocke.employee.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.dao.EmployeeDatabaseEntry;
import se.jocke.employee.test.builder.EmployeeTestBuilder;

public class TestEmployee {
    private static final Employee EMPLOYEE = EmployeeTestBuilder.bygg();

    @Test
    public void testThatEmployeeisCreated() {
        Employee employee = Employee.builder()
                .employeeId(EMPLOYEE.getEmployeeId())
                .firstName(EMPLOYEE.getFirstName())
                .lastName(EMPLOYEE.getLastName())
                .salary(EMPLOYEE.getSalary())
                .fullTime(EMPLOYEE.getFullTime())
                .departmentId(EMPLOYEE.getDepartmentId())
                .build();

        Assertions.assertEquals(EMPLOYEE, employee);
        Assertions.assertEquals(EMPLOYEE.getEmployeeId(), employee.getEmployeeId());
        Assertions.assertEquals(EMPLOYEE.getFirstName(), employee.getFirstName());
        Assertions.assertEquals(EMPLOYEE.getLastName(), employee.getLastName());
        Assertions.assertEquals(EMPLOYEE.getSalary(), employee.getSalary());
        Assertions.assertEquals(EMPLOYEE.getDepartmentId(), employee.getDepartmentId());
        String EMPLOYEE_TO_STRING_VALUE = EMPLOYEE.toString();
        String employee_to_string_value = employee.toString();
        Assertions.assertEquals(EMPLOYEE_TO_STRING_VALUE, employee_to_string_value);
    }

    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingEmployeeId() {
        Assertions.assertThrows(NullPointerException.class,
                () -> EmployeeDatabaseEntry.builder()
                        .firstName(EMPLOYEE.getFirstName())
                        .lastName(EMPLOYEE.getLastName())
                        .salary(EMPLOYEE.getSalary())
                        .fullTime(EMPLOYEE.getFullTime())
                        .departmentId(EMPLOYEE.getDepartmentId())
                        .build());
    }

    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingFirstName() {
        Assertions.assertThrows(NullPointerException.class,
                () -> EmployeeDatabaseEntry.builder()
                        .employeeId(EMPLOYEE.getEmployeeId().getId())
                        .lastName(EMPLOYEE.getLastName())
                        .salary(EMPLOYEE.getSalary())
                        .fullTime(EMPLOYEE.getFullTime())
                        .departmentId(EMPLOYEE.getDepartmentId())
                        .build());
    }

    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingLastName() {
        Assertions.assertThrows(NullPointerException.class,
                () -> EmployeeDatabaseEntry.builder()
                        .employeeId(EMPLOYEE.getEmployeeId().getId())
                        .firstName(EMPLOYEE.getFirstName())
                        .salary(EMPLOYEE.getSalary())
                        .fullTime(EMPLOYEE.getFullTime())
                        .departmentId(EMPLOYEE.getDepartmentId())
                        .build());
    }

    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingSalary() {
        Assertions.assertThrows(NullPointerException.class,
                () -> EmployeeDatabaseEntry.builder()
                        .employeeId(EMPLOYEE.getEmployeeId().getId())
                        .firstName(EMPLOYEE.getFirstName())
                        .lastName(EMPLOYEE.getLastName())
                        .fullTime(EMPLOYEE.getFullTime())
                        .departmentId(EMPLOYEE.getDepartmentId())
                        .build());
    }

    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingFullTime() {
        Assertions.assertThrows(NullPointerException.class,
                () -> EmployeeDatabaseEntry.builder()
                        .employeeId(EMPLOYEE.getEmployeeId().getId())
                        .firstName(EMPLOYEE.getFirstName())
                        .lastName(EMPLOYEE.getLastName())
                        .salary(EMPLOYEE.getSalary())
                        .departmentId(EMPLOYEE.getDepartmentId())
                        .build());
    }
    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingDepartmentId() {
        Assertions.assertThrows(NullPointerException.class,
                () -> EmployeeDatabaseEntry.builder()
                        .employeeId(EMPLOYEE.getEmployeeId().getId())
                        .firstName(EMPLOYEE.getFirstName())
                        .lastName(EMPLOYEE.getLastName())
                        .salary(EMPLOYEE.getSalary())
                        .fullTime(EMPLOYEE.getFullTime())
                        .build());
    }
}
