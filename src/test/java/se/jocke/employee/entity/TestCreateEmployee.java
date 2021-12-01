package se.jocke.employee.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.jocke.employee.builder.EmployeeTestBuilder;


public class TestCreateEmployee {
    private static final Employee ACTUAL_EMPLOYEE = EmployeeTestBuilder.builder().build();

    @Test
    @DisplayName("Match actual and expected Employee obj and fields")
    public void testCreateEmployee() {
        Employee EXPECTED_EMPLOYEE = Employee.builder()
                .employeeId(ACTUAL_EMPLOYEE.getEmployeeId())
                .firstName(ACTUAL_EMPLOYEE.getFirstName())
                .lastName(ACTUAL_EMPLOYEE.getLastName())
                .salary(ACTUAL_EMPLOYEE.getSalary())
                .fullTime(ACTUAL_EMPLOYEE.getFullTime())
                .departmentId(ACTUAL_EMPLOYEE.getDepartmentId())
                .build();

        Assertions.assertEquals(ACTUAL_EMPLOYEE, EXPECTED_EMPLOYEE);

        Assertions.assertAll(
                () -> Assertions.assertEquals(ACTUAL_EMPLOYEE.getEmployeeId().getId(), EXPECTED_EMPLOYEE.getEmployeeId().getId()),
                () -> Assertions.assertEquals(ACTUAL_EMPLOYEE.getFirstName(), EXPECTED_EMPLOYEE.getFirstName()),
                () -> Assertions.assertEquals(ACTUAL_EMPLOYEE.getLastName(), EXPECTED_EMPLOYEE.getLastName()),
                () -> Assertions.assertEquals(ACTUAL_EMPLOYEE.getSalary(), EXPECTED_EMPLOYEE.getSalary()),
                () -> Assertions.assertEquals(ACTUAL_EMPLOYEE.getFullTime(), EXPECTED_EMPLOYEE.getFullTime()),
                () -> Assertions.assertEquals(ACTUAL_EMPLOYEE.getDepartmentId(), EXPECTED_EMPLOYEE.getDepartmentId()));
    }
}