package se.jocke.employee.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.builder.EmployeeTestBuilder;

import java.math.BigDecimal;

public class TestCreateEmployee {
    private final Employee EMPLOYEE = EmployeeTestBuilder.builder();

    @Test
    public void testCreateEmployee() {
        Employee employee = Employee.builder()
                .employeeId(EMPLOYEE.getEmployeeId())
                .firstName("Monthy")
                .lastName("Python")
                .salary(new BigDecimal(20))
                .fullTime(true)
                .departmentId(10)
                .build();

        Assertions.assertEquals(EMPLOYEE, employee);

    }

    @Test
    public void testCreateDepartmentThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            Employee.builder().build();
        });
    }
}