package se.jocke.employee.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCreateEmployee {

    private final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();

    @Test
    public void TestCreateEmployee() {
        Employee employee = EmployeeTestBuilder.builder()
                .employeeId(EMPLOYEE.getEmployeeId())
                .departmentId(EMPLOYEE.getDepartmentId())
                .salary(EMPLOYEE.getSalary())
                .firstName(EMPLOYEE.getFirstName())
                .lastName(EMPLOYEE.getLastName())
                .fullTime(EMPLOYEE.getFullTime())
                .build();

        Assertions.assertAll(
                () -> assertEquals(EMPLOYEE.getEmployeeId(), employee.getEmployeeId()),
                () -> assertEquals(EMPLOYEE.getDepartmentId(), employee.getDepartmentId()),
                () -> assertEquals(EMPLOYEE.getSalary(), employee.getSalary()),
                () -> assertEquals(EMPLOYEE.getFirstName(), employee.getFirstName()),
                () -> assertEquals(EMPLOYEE.getLastName(), employee.getLastName()),
                () -> assertEquals(EMPLOYEE.getFullTime(), employee.getFullTime())
        );

    }

    @Test
    public void testCreateEmployeeException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            Employee.builder().firstName(EMPLOYEE.getFirstName()).build();

        });

    }
}
