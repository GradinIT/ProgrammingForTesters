package se.jocke.employee.unittests.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.dao.EmployeeDatabaseEntry;
import se.jocke.employee.dao.EmployeeDatabaseEntryMapper;
import se.jocke.employee.unittests.Builder.EmployeeDatabaseEntryTestBuilder;
import se.jocke.employee.unittests.Builder.EmployeeTestBuilder;
import se.jocke.employee.entity.Employee;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestEmployeeDatabaseEntryMapper {
    private final EmployeeDatabaseEntry EMPLOYEE_DATABASE_ENTRY = EmployeeDatabaseEntryTestBuilder.build();
    private final Employee EMPLOYEE = EmployeeTestBuilder.build();

    @Test
    public void testEmployeeToEmployeeDatabaseEntryMapping() {
        EmployeeDatabaseEntry databaseEntry = EmployeeDatabaseEntryMapper.map(EMPLOYEE);
        Assertions.assertAll(
                () -> assertEquals(EMPLOYEE.getEmployeeId().getId(), databaseEntry.getEmployeeId()),
                () -> assertEquals(EMPLOYEE.getFirstName(), databaseEntry.getFirstName()),
                () -> assertEquals(EMPLOYEE.getLastName(), databaseEntry.getLastName()),
                () -> assertEquals(EMPLOYEE.getSalary(), databaseEntry.getSalary()),
                () -> assertEquals(EMPLOYEE.getFullTime(), databaseEntry.getFullTime()),
                () -> assertEquals(EMPLOYEE.getDepartmentId(), databaseEntry.getDepartmentId())
        );
    }

    @Test
    public void testEmployeeDatabaseEntryToEmployeeMapping() {
        Employee employee = EmployeeDatabaseEntryMapper.map(EMPLOYEE_DATABASE_ENTRY);
        Assertions.assertAll(
                () -> assertEquals(EMPLOYEE_DATABASE_ENTRY.getEmployeeId(), employee.getEmployeeId().getId()),
                () -> assertEquals(EMPLOYEE_DATABASE_ENTRY.getFirstName(), employee.getFirstName()),
                () -> assertEquals(EMPLOYEE_DATABASE_ENTRY.getLastName(), employee.getLastName()),
                () -> assertEquals(EMPLOYEE_DATABASE_ENTRY.getSalary(), employee.getSalary()),
                () -> assertEquals(EMPLOYEE_DATABASE_ENTRY.getFullTime(), employee.getFullTime()),
                () -> assertEquals(EMPLOYEE_DATABASE_ENTRY.getDepartmentId(), employee.getDepartmentId())
        );
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
