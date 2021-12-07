package se.jocke.employee.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.test.builder.EmployeeDatabaseEntryTestBuilder;
import se.jocke.employee.test.builder.EmployeeTestBuilder;

import java.util.Arrays;
import java.util.List;

public class TestEmployeeDatabaseEntryMapper {
    private final Employee EMPLOYEE = EmployeeTestBuilder.bygg();
    private final EmployeeDatabaseEntry EMPLOYEE_DATABASE_ENTRY = EmployeeDatabaseEntryTestBuilder.bygg();

    @Test
    public void TestEmployeeToEmployeeDatabaseEntry(){
        EmployeeDatabaseEntry entry = EmployeeDatabaseEntryMapper.map(EMPLOYEE);
        Assertions.assertNotNull(entry);
        Assertions.assertEquals(EMPLOYEE_DATABASE_ENTRY, entry);
    }

    @Test
    public void testEmployeeDatabaseEntryToEmployee(){
        Employee employee = EmployeeDatabaseEntryMapper.map(EMPLOYEE_DATABASE_ENTRY);
        Assertions.assertNotNull(employee);
        Assertions.assertEquals(EMPLOYEE, employee);
    }
    
    @Test
    public void testEntriesToEmployees() {
        List<Employee> EMPLOYEES = Arrays.asList(EMPLOYEE);
        List<EmployeeDatabaseEntry> ENTRIES = Arrays.asList(EMPLOYEE_DATABASE_ENTRY);
        List<Employee> employees = EmployeeDatabaseEntryMapper.map(ENTRIES);
        Assertions.assertNotNull(employees);
        Assertions.assertEquals(EMPLOYEES, employees);
    }
}
