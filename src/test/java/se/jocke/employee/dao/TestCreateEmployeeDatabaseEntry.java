package se.jocke.employee.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.Builder.EmployeeDatabaseEntryTestBuilder;
import se.jocke.employee.entity.Employee;

public class TestCreateEmployeeDatabaseEntry {
    private final EmployeeDatabaseEntry EMPLOYEE = EmployeeDatabaseEntryTestBuilder.build();

    @Test
    public void testCreateEmployee() {
        EmployeeDatabaseEntry employee = EmployeeDatabaseEntry.builder()
                .employeeId(EMPLOYEE.getEmployeeId())
                .firstName(EMPLOYEE.getFirstName())
                .lastName(EMPLOYEE.getLastName())
                .salary(EMPLOYEE.getSalary())
                .fullTime(EMPLOYEE.getFullTime())
                .departmentId(EMPLOYEE.getDepartmentId())
                .build();

        Assertions.assertEquals(EMPLOYEE, employee);
        Assertions.assertEquals(EMPLOYEE.getEmployeeId(), employee.getEmployeeId());
        Assertions.assertEquals(EMPLOYEE.getFirstName(), employee.getFirstName());
        Assertions.assertEquals(EMPLOYEE.getLastName(), employee.getLastName());
        Assertions.assertEquals(EMPLOYEE.getSalary(), employee.getSalary());
        Assertions.assertEquals(EMPLOYEE.getFullTime(), employee.getFullTime());
        Assertions.assertEquals(EMPLOYEE.getDepartmentId(), employee.getDepartmentId());
    }

}

