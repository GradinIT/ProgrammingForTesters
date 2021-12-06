package se.jocke.employee.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.Builder.EmployeeDatabaseEntryTestBuilder;
import se.jocke.employee.Builder.EmployeeModelTestBuilder;
import se.jocke.employee.Builder.EmployeeTestBuilder;
import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.api.mapper.EmployeeModelMapper;
import se.jocke.employee.entity.Employee;

import static org.junit.Assert.assertEquals;

public class TestEmployeeDatabaseEntryMapper {
    private final EmployeeDatabaseEntry EMPLOYEEDATABASEENTRY = EmployeeDatabaseEntryTestBuilder.build();
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
        Employee employee = EmployeeDatabaseEntryMapper.map(EMPLOYEEDATABASEENTRY);
        Assertions.assertAll(
                () -> assertEquals(EMPLOYEEDATABASEENTRY.getEmployeeId(), employee.getEmployeeId().getId()),
                () -> assertEquals(EMPLOYEEDATABASEENTRY.getFirstName(), employee.getFirstName()),
                () -> assertEquals(EMPLOYEEDATABASEENTRY.getLastName(), employee.getLastName()),
                () -> assertEquals(EMPLOYEEDATABASEENTRY.getSalary(), employee.getSalary()),
                () -> assertEquals(EMPLOYEEDATABASEENTRY.getFullTime(), employee.getFullTime()),
                () -> assertEquals(EMPLOYEEDATABASEENTRY.getDepartmentId(), employee.getDepartmentId())
        );
    }
}
