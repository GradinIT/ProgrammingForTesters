package se.jocke.employee.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;

public class TestCreateEmployee {
    private final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();

    @Test
    public void testCreateEmployee() {
        Employee employee = EmployeeTestBuilder.builder()
                .employeeId(EMPLOYEE.getEmployeeId())
                .firstName(EMPLOYEE.getFirstName())
                .lastName(EMPLOYEE.getLastName())
                .salary(EMPLOYEE.getSalary())
                .departmentId(EMPLOYEE.getDepartmentId())
                .fullTime(EMPLOYEE.getFullTime())
                .build();
        Assertions.assertEquals(EMPLOYEE, employee);
        Assertions.assertEquals(EMPLOYEE.getEmployeeId(), employee.getEmployeeId());
        Assertions.assertEquals(EMPLOYEE.getFirstName(), employee.getFirstName());
        Assertions.assertEquals(EMPLOYEE.getLastName(), employee.getLastName());
        Assertions.assertEquals(EMPLOYEE.getSalary(), employee.getSalary());
        Assertions.assertEquals(EMPLOYEE.getDepartmentId(), employee.getDepartmentId());
        Assertions.assertEquals(EMPLOYEE.getFullTime(), employee.getFullTime());
    }

    @Test
    public void testCreateEmployeeTrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
           Employee.builder().employeeId(EMPLOYEE.getEmployeeId()).build();
        });
    }

}
