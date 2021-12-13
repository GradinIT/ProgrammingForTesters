package se.jocke.employee.unittest.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.dao.EmployeeDatabaseEntry;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.test.builder.EmployeeDatabaseEntryTestBuilder;
import se.jocke.employee.test.builder.EmployeeTestBuilder;

import java.util.Arrays;
import java.util.List;

public class EmployeeDatabaseEntryMapper {

    private final Employee EMPLOYEE = EmployeeTestBuilder.build();
    private final EmployeeDatabaseEntry EMPLOYEE_DATABASE_ENTRY = EmployeeDatabaseEntryTestBuilder.build();

    @Test
    public void testEmployeeToDatabaseEntry() {
        EmployeeDatabaseEntry entry = se.jocke.employee.dao.EmployeeDatabaseEntryMapper.map(EMPLOYEE);
        Assertions.assertNotNull(entry);
        Assertions.assertEquals(EMPLOYEE_DATABASE_ENTRY,entry);
    }

    @Test
    public void testDatabaseEntryToEmployee() {
        Employee employee = se.jocke.employee.dao.EmployeeDatabaseEntryMapper.map(EMPLOYEE_DATABASE_ENTRY);
        Assertions.assertNotNull(employee);
        Assertions.assertEquals(EMPLOYEE,employee);
    }

    @Test
    public void testEntriesToDepartments() {
        List<Employee> EMPLOYEES = Arrays.asList(EMPLOYEE);
        List<EmployeeDatabaseEntry> ENTRIES = Arrays.asList(EMPLOYEE_DATABASE_ENTRY);
        List<Employee> employees = se.jocke.employee.dao.EmployeeDatabaseEntryMapper.map(ENTRIES);
        Assertions.assertNotNull(employees);
        Assertions.assertEquals(EMPLOYEES,employees);
    }
}
