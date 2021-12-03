package se.jocke.employee.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import se.jocke.department.dao.DepartmentDatabaseEntry;
import se.jocke.department.test.builder.DepartmentDatabaseEntryTestBuilder;
import se.jocke.employee.builder.EmployeeDatabaseEntryTestBuilder;

public class TestEmployeeDatabaseEntry {
    private static final EmployeeDatabaseEntry ENTRY = EmployeeDatabaseEntryTestBuilder.build();

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

        Assertions.assertEquals(ENTRY,employee);
        Assertions.assertEquals(ENTRY.getFirstName(), employee.getFirstName());
        Assertions.assertEquals(ENTRY.getEmployeeId(), employee.getEmployeeId());
        String EMPLOYEE_TO_STRING_VALUE = ENTRY.toString();
        String employee_to_string_value = employee.toString();
        Assertions.assertEquals(EMPLOYEE_TO_STRING_VALUE,employee_to_string_value);
    }
    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingFirstName() {
        Assertions.assertThrows(NullPointerException.class ,
                () -> EmployeeDatabaseEntry.builder().firstName(ENTRY.getFirstName()).build());

    }
    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingEmployeeId() {
        Assertions.assertThrows(NullPointerException.class ,
                () -> EmployeeDatabaseEntry.builder().employeeId(ENTRY.getEmployeeId()).build());

    }
}