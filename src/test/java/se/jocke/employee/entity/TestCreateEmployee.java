package se.jocke.employee.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.jocke.department.entity.Employee;
import se.jocke.department.entity.EmployeeID;
import se.jocke.employee.builder.EmployeeTestBuilder;

import static org.junit.jupiter.api.Assertions.*;

public class TestCreateEmployee {

    private final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();

    @Test
    @DisplayName("Test create employee")
    public void testCreateEmployee() {

        Employee employee = Employee.builder()
                .employeeId(EMPLOYEE.getEmployeeId())
                .firstName(EMPLOYEE.getFirstName())
                .lastName(EMPLOYEE.getLastName())
                .fullTime(EMPLOYEE.getFullTime())
                .salary(EMPLOYEE.getSalary())
                .departmentId(EMPLOYEE.getDepartmentId())
                .build();

        assertAll(
                () -> assertEquals(EMPLOYEE.getEmployeeId(), employee.getEmployeeId()),
                () -> assertEquals(EMPLOYEE.getFirstName(), employee.getFirstName()),
                () -> assertEquals(EMPLOYEE.getLastName(), employee.getLastName()),
                () -> assertEquals(EMPLOYEE.getFullTime(), employee.getFullTime()),
                () -> assertEquals(EMPLOYEE.getSalary(), employee.getSalary()),
                () -> assertEquals(EMPLOYEE.getDepartmentId(), employee.getDepartmentId())
        );
    }

    @Test
    @DisplayName("Test create employee throws NullPointerException")
    public void testCreateEmployeeThrowsNullPointerException() {
        
        assertThrows(NullPointerException.class,
                () -> Employee.builder().employeeId(EmployeeID.builder().id(1).build()).build());
    }

}
