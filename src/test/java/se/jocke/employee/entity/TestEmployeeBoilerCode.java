package se.jocke.employee.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.builder.EmployeeModelTestBuilder;

public class TestEmployeeBoilerCode {
    private final EmployeeModel ACTUAL_EMPLOYEE = EmployeeModelTestBuilder.builder().build();
    private final EmployeeModel EXPECTED_EMPLOYEE = EmployeeModelTestBuilder.builder().build();

    @Test
    @DisplayName("Test overridden to_String method")
    public void testToStringMethod() {
        Assertions.assertEquals(ACTUAL_EMPLOYEE.toString(), EXPECTED_EMPLOYEE.toString());
    }

    @Test
    @DisplayName("Test equals()/hashcode()")
    public void testReturnTrueEqualsHashcode() {
        // Assume obj equals
        Assumptions.assumeTrue(ACTUAL_EMPLOYEE.equals(EXPECTED_EMPLOYEE));
        Assertions.assertEquals(ACTUAL_EMPLOYEE.hashCode(), EXPECTED_EMPLOYEE.hashCode());
    }

    @Test
    @DisplayName("Test Employee getters")
    public void testGetterMethods() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(ACTUAL_EMPLOYEE.getEmployeeId(), EXPECTED_EMPLOYEE.getEmployeeId()),
                () -> Assertions.assertEquals(ACTUAL_EMPLOYEE.getFirstName(), EXPECTED_EMPLOYEE.getFirstName()),
                () -> Assertions.assertEquals(ACTUAL_EMPLOYEE.getLastName(), EXPECTED_EMPLOYEE.getLastName()),
                () -> Assertions.assertEquals(ACTUAL_EMPLOYEE.getSalary(), EXPECTED_EMPLOYEE.getSalary()),
                () -> Assertions.assertEquals(ACTUAL_EMPLOYEE.getFullTime(), EXPECTED_EMPLOYEE.getFullTime()),
                () -> Assertions.assertEquals(ACTUAL_EMPLOYEE.getDepartmentId(), EXPECTED_EMPLOYEE.getDepartmentId()));
    }
}