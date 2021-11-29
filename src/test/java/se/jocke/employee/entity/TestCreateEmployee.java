package se.jocke.employee.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.builder.EmployeeTestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCreateEmployee {
    private final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();

    @Test
    public void testCreateEmployee() {
        Employee employee = Employee.builder()
                .departmentId(EMPLOYEE.getDepartmentId())
                .firstName(EMPLOYEE.getFirstName())
                .lastName(EMPLOYEE.getLastName())
                .salary(EMPLOYEE.getSalary())
                .fullTime(EMPLOYEE.getFullTime())
                .employeeId(EMPLOYEE.getEmployeeId())
                .build();


        Assertions.assertEquals(EMPLOYEE.getFirstName(), employee.getFirstName());
        Assertions.assertEquals(EMPLOYEE.getDepartmentId(), employee.getDepartmentId());
        Assertions.assertEquals(EMPLOYEE.getEmployeeId(), employee.getEmployeeId());
        Assertions.assertEquals(EMPLOYEE.getLastName(), employee.getLastName());
        Assertions.assertEquals(EMPLOYEE.getSalary(), employee.getSalary());
        Assertions.assertEquals(EMPLOYEE.getFullTime(), employee.getFullTime());
        Assertions.assertEquals(EMPLOYEE,employee);
    }

    @Test
    public void testCreateEmployeeThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            Employee.builder().build();
        });
    }
}
