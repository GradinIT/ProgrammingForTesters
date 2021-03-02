package se.jocke.employee.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;

public class TestCreateEmployee {
    private final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();

    @Test
    public void testCreateEmployee() {
        Employee department = EmployeeTestBuilder.builder()
                .employeeId(EMPLOYEE.getEmployeeId())
                .firstName(EMPLOYEE.getFirstName())
                .build();
        Assertions.assertEquals(EMPLOYEE, department);
        Assertions.assertEquals(EMPLOYEE.getFirstName(), department.getFirstName());
        Assertions.assertEquals(EMPLOYEE.getEmployeeId(), department.getEmployeeId());
    }

    @Test
    public void testCreateEmployeeThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            Employee.builder().employeeId(EMPLOYEE.getEmployeeId()).build();
        });
    }
}
