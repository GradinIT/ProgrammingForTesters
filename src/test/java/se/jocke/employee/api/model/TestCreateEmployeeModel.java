package se.jocke.employee.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import se.jocke.api.EmployeeModel;
import se.jocke.employee.builder.EmployeeModelTestBuilder;

@DisplayName("Test create employee model")
public class TestCreateEmployeeModel {

    private final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.builder().build();

    @Test
    @DisplayName("Given that everything is OK")
    public void testCreateEmployeeModelHappyFlow() {

        EmployeeModel employeeModel = EmployeeModel.builder()
                .employeeId(EMPLOYEE_MODEL.getEmployeeId())
                .firstName(EMPLOYEE_MODEL.getFirstName())
                .lastName(EMPLOYEE_MODEL.getLastName())
                .salary(EMPLOYEE_MODEL.getSalary())
                .fullTime(EMPLOYEE_MODEL.getFullTime())
                .departmentId(EMPLOYEE_MODEL.getDepartmentId())
                .build();

        Assertions.assertAll(
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getEmployeeId(), employeeModel.getEmployeeId()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getFirstName(), employeeModel.getFirstName()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getLastName(), employeeModel.getLastName()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getSalary(), employeeModel.getSalary()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getFullTime(), employeeModel.getFullTime()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getDepartmentId(), employeeModel.getDepartmentId())
        );
    }

    @Test
    @DisplayName("Given that something is null")
    public void testCreateEmployeeModelThrowsNullPointerException() {
        Assertions.assertThrows(NullPointerException.class,
                () -> {
                    EmployeeModelTestBuilder.builder()
                            .firstName(null)
                            .build();
                });
    }
}

