package se.jocke.employee.unittest.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.dao.EmployeeDatabaseEntry;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.test.builder.EmployeeDatabaseEntryTestBuilder;
import se.jocke.employee.test.builder.EmployeeTestBuilder;
import se.jocke.employee.dao.EmployeeDatabaseEntryMapper;

import java.util.Arrays;
import java.util.List;

public class TestEmployeeDatabaseEntryMapper {

    private final Employee EMPLOYEE = EmployeeTestBuilder.build();
    private final EmployeeDatabaseEntry EMPLOYEE_DATABASE_ENTRY = EmployeeDatabaseEntryTestBuilder.build();

    @Test
    public void testEmployeeToDatabaseEntry() {
        // vi skapar entry som mappar EmployeeTestBuilder (EMPLOYEE)
        EmployeeDatabaseEntry entry = EmployeeDatabaseEntryMapper.map(EMPLOYEE);
        Assertions.assertNotNull(entry);
        //---
        Assertions.assertEquals(EMPLOYEE_DATABASE_ENTRY,entry);
    }

    @Test
    public void testDatabaseEntryToEmployee() { // samma som förgående test fast tvärtom
        Employee employee = EmployeeDatabaseEntryMapper.map(EMPLOYEE_DATABASE_ENTRY);
        Assertions.assertNotNull(employee);
        Assertions.assertEquals(EMPLOYEE,employee);
    }

    @Test
    public void testEntriesToEmployees() {
        // tar parametrarna ifrån EMPLOYEE och skapar en List
        List<Employee> EMPLOYEES = Arrays.asList(EMPLOYEE);
        // skapar ENTRIES som är en list av EMPLOYEE_DATABASE_ENTRY
        List<EmployeeDatabaseEntry> ENTRIES = Arrays.asList(EMPLOYEE_DATABASE_ENTRY);
        // Skapar employees som ärr ENTRIES som en map
        List<Employee> employees = EmployeeDatabaseEntryMapper.map(ENTRIES);
        Assertions.assertNotNull(employees);
        Assertions.assertEquals(EMPLOYEES,employees);
    }
}


