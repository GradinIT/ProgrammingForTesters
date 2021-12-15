package se.jocke.employee.unittest.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.dao.EmployeeDatabaseEntry;
import se.jocke.employee.test.builder.EmployeeDatabaseEntryTestBuilder;


public class TestEmployeeDatabaseEntry {
    private static final EmployeeDatabaseEntry ENTRY = EmployeeDatabaseEntryTestBuilder.build();

    @Test
    public void testThatEmployeeIsCreated () {
        EmployeeDatabaseEntry employee = EmployeeDatabaseEntry.builder()
                .departmentId(ENTRY.getDepartmentId())
                .firstName(ENTRY.getFirstName())
                .salary(ENTRY.getSalary())
                .lastName(ENTRY.getLastName())
                .fullTime(ENTRY.getFullTime())
                .employeeId(ENTRY.getEmployeeId())
                .build();

        Assertions.assertEquals(ENTRY,employee);
        Assertions.assertEquals(ENTRY.getFirstName(), employee.getFirstName());
        Assertions.assertEquals(ENTRY.getDepartmentId(), employee.getDepartmentId());
        Assertions.assertEquals(ENTRY.getLastName(),employee.getLastName());
        Assertions.assertEquals(ENTRY.getSalary(),employee.getSalary());
        Assertions.assertEquals(ENTRY.getFullTime(),employee.getFullTime());
        Assertions.assertEquals(ENTRY.getEmployeeId(),employee.getEmployeeId());

        String EMPLOYEE_TO_STRING_VALUE = ENTRY.toString();
        String employee_to_string_value = employee.toString();
        Assertions.assertEquals(EMPLOYEE_TO_STRING_VALUE,employee_to_string_value);
    }

}
