package se.jocke.employee.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.util.StringUtils;
import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.builder.EmployeeModelTestBuilder;

import java.util.stream.Stream;

public class TestDataFieldsAndExceptions {
    private static final EmployeeModel ACTUAL_EMPLOYEE = EmployeeModelTestBuilder.builder().build();


    @ParameterizedTest
    @DisplayName("Test if empty first and/or lastname")
    @MethodSource("stringFields")
    public void testShouldReturnFalseEmptyStringFirstLastName(String parameter) {
        Assertions.assertFalse(StringUtils.isBlank(parameter));
    }

    static Stream<String> stringFields() {
        return Stream.of(ACTUAL_EMPLOYEE.getFirstName(), ACTUAL_EMPLOYEE.getLastName());
    }

    @Test
    @DisplayName("Test Throws Exception - NullPointerException")
    public void testCreateEmployeeThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            Employee.builder().build();
        });
    }
}