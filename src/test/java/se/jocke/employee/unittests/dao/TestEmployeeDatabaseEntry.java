package se.jocke.employee.unittests.dao;

import io.cucumber.java.bs.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.unittests.Builder.EmployeeDatabaseEntryTestBuilder;
import se.jocke.employee.unittests.entity.Employee;

public class TestEmployeeDatabaseEntry {
    private static final EmployeeDatabaseEntry ENTRY = EmployeeDatabaseEntryTestBuilder.build();

    @Test
    public void testThatEmployeeIsCreated() {
        EmployeeDatabaseEntry employee = EmployeeDatabaseEntry.builder()
                .employeeId(ENTRY.getEmployeeId())
                .firstName(ENTRY.getFirstName())
                .lastName(ENTRY.getLastName())
                .salary(ENTRY.getSalary())
                .fullTime(ENTRY.getFullTime())
                .departmentId(ENTRY.getDepartmentId())
                .build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(ENTRY,employee),
                () -> Assertions.assertEquals(ENTRY.getEmployeeId(), employee.getEmployeeId()),
                () -> Assertions.assertEquals(ENTRY.getFirstName(), employee.getFirstName()),
                () -> Assertions.assertEquals(ENTRY.getLastName(),employee.getLastName()),
                () -> Assertions.assertEquals(ENTRY.getDepartmentId(), employee.getDepartmentId()),
                () -> Assertions.assertEquals(ENTRY.getFullTime(),employee.getFullTime()),
                () -> Assertions.assertEquals(ENTRY.getSalary(),employee.getSalary()));

        String EMPLOYEE_TO_STRING_VALUE = ENTRY.toString();
        String employee_to_string_value = employee.toString();
        Assertions.assertEquals(EMPLOYEE_TO_STRING_VALUE,employee_to_string_value);
    }
    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingEmployeeId() {
        Assertions.assertThrows(NullPointerException.class,
                () -> EmployeeDatabaseEntry.builder().firstName(ENTRY.getFirstName()).build());
    }

    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingEmployeeFirstName() {
        Assertions.assertThrows(NullPointerException.class,
                () -> EmployeeDatabaseEntry.builder().employeeId(ENTRY.getEmployeeId()).build());
    }

    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingEmployeeLastName() {
        Assertions.assertThrows(NullPointerException.class,
                () -> EmployeeDatabaseEntry.builder().employeeId(ENTRY.getEmployeeId()).build());
    }

    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingEmployeeSalary() {
        Assertions.assertThrows(NullPointerException.class,
                () -> EmployeeDatabaseEntry.builder().employeeId(ENTRY.getEmployeeId()).build());
    }

    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingEmployeeFulltime() {
        Assertions.assertThrows(NullPointerException.class,
                () -> EmployeeDatabaseEntry.builder().employeeId(ENTRY.getEmployeeId()).build());
    }

    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingEmployeeDepartmentId() {
        Assertions.assertThrows(NullPointerException.class,
                () -> EmployeeDatabaseEntry.builder().employeeId(ENTRY.getEmployeeId()).build());
    }
}
