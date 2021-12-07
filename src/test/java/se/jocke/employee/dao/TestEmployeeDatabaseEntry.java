package se.jocke.employee.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.test.builder.EmployeeDatabaseEntryTestBuilder;

public class TestEmployeeDatabaseEntry {
    private static final EmployeeDatabaseEntry ENTRY = EmployeeDatabaseEntryTestBuilder.bygg();

    @Test
    public void testThatEmployeeIsCreated () {
        EmployeeDatabaseEntry employee = EmployeeDatabaseEntry.builder()
                .employeeId(ENTRY.getEmployeeId())
                .firstName(ENTRY.getFirstName())
                .lastName(ENTRY.getLastName())
                .salary(ENTRY.getSalary())
                .fullTime(ENTRY.getFullTime())
                .departmentId(ENTRY.getDepartmentId())
                .build();

        Assertions.assertEquals(ENTRY, employee);
        Assertions.assertEquals(ENTRY.getEmployeeId(), employee.getEmployeeId());
        Assertions.assertEquals(ENTRY.getFirstName(), employee.getFirstName());
        Assertions.assertEquals(ENTRY.getLastName(), employee.getLastName());
        Assertions.assertEquals(ENTRY.getSalary(), employee.getSalary());
        Assertions.assertEquals(ENTRY.getFullTime(), employee.getFullTime());
        Assertions.assertEquals(ENTRY.getDepartmentId(), employee.getDepartmentId());
        String EMPLOYEE_TO_STRING_VALUE = ENTRY.toString();
        String employee_to_string_value = employee.toString();
        Assertions.assertEquals(EMPLOYEE_TO_STRING_VALUE, employee_to_string_value);
    }

    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingEmployeeId(){
        Assertions.assertThrows(NullPointerException.class,
                ()-> EmployeeDatabaseEntry.builder()
                        .firstName(ENTRY.getFirstName())
                        .lastName(ENTRY.getLastName())
                        .salary(ENTRY.getSalary())
                        .fullTime(ENTRY.getFullTime())
                        .departmentId(ENTRY.getDepartmentId())
                        .build()
    );

    }
    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingFirstName() {
        Assertions.assertThrows(NullPointerException.class,
                ()-> EmployeeDatabaseEntry.builder()
                        .employeeId(ENTRY.getEmployeeId())
                        .lastName(ENTRY.getLastName())
                        .salary(ENTRY.getSalary())
                        .fullTime(ENTRY.getFullTime())
                        .departmentId(ENTRY.getDepartmentId())
                        .build());
    }
    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingLastName() {
       Assertions.assertThrows(NullPointerException.class,
                ()-> EmployeeDatabaseEntry.builder()
                        .employeeId(ENTRY.getEmployeeId())
                        .firstName(ENTRY.getFirstName())
                        .salary(ENTRY.getSalary())
                        .fullTime(ENTRY.getFullTime())
                        .departmentId(ENTRY.getDepartmentId())
                        .build());
    }
    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingSalary() {
       Assertions.assertThrows(NullPointerException.class,
                ()-> EmployeeDatabaseEntry.builder()
                        .employeeId(ENTRY.getEmployeeId())
                        .firstName(ENTRY.getFirstName())
                        .lastName(ENTRY.getLastName())
                        .fullTime(ENTRY.getFullTime())
                        .departmentId(ENTRY.getDepartmentId())
                        .build());
    }
    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingFullTime() {
       Assertions.assertThrows(NullPointerException.class,
                ()-> EmployeeDatabaseEntry.builder()
                        .employeeId(ENTRY.getEmployeeId())
                        .firstName(ENTRY.getFirstName())
                        .lastName(ENTRY.getLastName())
                        .salary(ENTRY.getSalary())
                        .departmentId(ENTRY.getDepartmentId())
                        .build());
    }
    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingDepartmentId() {
        Assertions.assertThrows(NullPointerException.class,
                ()-> EmployeeDatabaseEntry.builder()
                        .employeeId(ENTRY.getEmployeeId())
                        .firstName(ENTRY.getFirstName())
                        .lastName(ENTRY.getLastName())
                        .salary(ENTRY.getSalary())
                        .fullTime(ENTRY.getFullTime())
                        .build());
    }
}
