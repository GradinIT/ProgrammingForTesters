package se.jocke.employee.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;

/**
 * Created by Oscar Bergström 01-03-2021
 */
public class TestCreateEmployee {

    // Create a dummy Employee to test against
    private final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();

    // Create a new employee with the same field values as our dummy, and Assert that they are equal
    @Test
    public void testCreateEmployee() {
        Employee employee = Employee.builder()
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

    // Test that the Employee builder throws an exception if the builder pattern does not get all the @NonNull fields it require
    @Test
    public void testCreateEmployeeThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> Employee.builder().firstName(EMPLOYEE.getFirstName()).build());
    }

}
