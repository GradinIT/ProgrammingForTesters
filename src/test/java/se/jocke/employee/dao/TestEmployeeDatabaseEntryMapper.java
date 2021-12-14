package se.jocke.employee.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.builder.EmployeeDatabaseEntryTestBuilder;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.employee.entity.Employee;
import java.util.Arrays;
import java.util.List;

public class TestEmployeeDatabaseEntryMapper {
    private final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();
    private final EmployeeDatabaseEntry EMPLOYEE_DATABASE_ENTRY = EmployeeDatabaseEntryTestBuilder.build();

    @Test
    public void testEmployeeToDatabaseEntry() {
        EmployeeDatabaseEntry entry = EmployeeDatabaseEntryMapper.map(EMPLOYEE);
        Assertions.assertNotNull(entry);
        Assertions.assertEquals(EMPLOYEE_DATABASE_ENTRY,entry);
    }

    @Test
    public void testDatabaseEntryToEmployee() {
        Employee employee = EmployeeDatabaseEntryMapper.map(EMPLOYEE_DATABASE_ENTRY);
        Assertions.assertNotNull(employee);
        Assertions.assertEquals(EMPLOYEE,employee);
    }

    @Test
    public void testEntriesToEmployee() {
        List<Employee> EMPLOYEES = Arrays.asList(EMPLOYEE);
        List<EmployeeDatabaseEntry> ENTRIES = Arrays.asList(EMPLOYEE_DATABASE_ENTRY);
        List<Employee> employees = EmployeeDatabaseEntryMapper.map(ENTRIES);
        Assertions.assertNotNull(employees);
        Assertions.assertEquals(EMPLOYEES,employees);
    }
}
