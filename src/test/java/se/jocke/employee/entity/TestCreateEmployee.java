package se.jocke.employee.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestCreateEmployee {
    public final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();

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

        Assertions.assertAll(
                () -> assertEquals(EMPLOYEE, employee),
                () -> assertEquals(EMPLOYEE.getEmployeeId(), employee.getEmployeeId()),
                () -> assertEquals(EMPLOYEE.getFirstName(), employee.getFirstName()),
                () -> assertEquals(EMPLOYEE.getLastName(), employee.getLastName()),
                () -> assertEquals(EMPLOYEE.getSalary(), employee.getSalary()),
                () -> assertEquals(EMPLOYEE.getFullTime(), employee.getFullTime()),
                () -> assertEquals(EMPLOYEE.getDepartmentId(), employee.getDepartmentId())
        );
    }

    @Test
    public void testCreateEmployeeThrowsException() {
        Throwable errorMessage = Assertions.assertThrows(NullPointerException.class, () ->{
            // Skapar en employee och "glömmer" lastName för att få fram NullpointerException
            Employee.builder()
                    .employeeId(EMPLOYEE.getEmployeeId())
                    .firstName(EMPLOYEE.getFirstName())
                    .salary(EMPLOYEE.getSalary())
                    .fullTime(EMPLOYEE.getFullTime())
                    .departmentId(EMPLOYEE.getDepartmentId())
                    .build();
        });
        Assertions.assertEquals("lastName is marked non-null but is null", errorMessage.getMessage());
    }

    @Test
    public void testCreateEmployeeThrowsExceptionVersion2() {
        String message = String.valueOf(Assertions.assertThrows(NullPointerException.class,
                () -> {
                    Employee.builder()
                            .employeeId(EMPLOYEE.getEmployeeId())
                            .firstName(EMPLOYEE.getFirstName())
                            .salary(EMPLOYEE.getSalary())
                            .fullTime(EMPLOYEE.getFullTime())
                            .departmentId(EMPLOYEE.getDepartmentId())
                            .build();
                }));
        assertEquals("java.lang.NullPointerException: lastName is marked non-null but is null", message);


    }
}
