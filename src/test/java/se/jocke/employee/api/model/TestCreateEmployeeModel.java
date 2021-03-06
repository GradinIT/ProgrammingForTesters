package se.jocke.employee.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.jocke.api.EmployeeModel;
import se.jocke.employee.builder.EmployeeModelTestBuilder;

public class TestCreateEmployeeModel {
    private final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.builder().build();

    // Tests for creating an employee objects with correct data
    // An object is built and then tested against the expected values
    // Also testing that the expected exception is thrown
    @DisplayName("Testing employee model creation")
    @Test
    public void testCreateEmployeeModel() {
        EmployeeModel employee = EmployeeModel.builder()
                .employeeId(EMPLOYEE_MODEL.getEmployeeId())
                .firstName(EMPLOYEE_MODEL.getFirstName())
                .lastName(EMPLOYEE_MODEL.getLastName())
                .salary(EMPLOYEE_MODEL.getSalary())
                .fullTime(EMPLOYEE_MODEL.getFullTime())
                .departmentId(EMPLOYEE_MODEL.getDepartmentId())
                .build();


                    Assertions.assertEquals(EMPLOYEE_MODEL.getEmployeeId(), employee.getEmployeeId());
                    Assertions.assertEquals(EMPLOYEE_MODEL.getFirstName(), employee.getFirstName());
                    Assertions.assertEquals(EMPLOYEE_MODEL.getLastName(), employee.getLastName());
                    Assertions.assertEquals(EMPLOYEE_MODEL.getSalary(), employee.getSalary());
                    Assertions.assertEquals(EMPLOYEE_MODEL.getFullTime(), employee.getFullTime());

    }

    @DisplayName("Testing that NullPointerException is thrown")
    @Test
    public void testCreateEmployeeModelThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            EmployeeModel.builder().employeeId(EMPLOYEE_MODEL.getEmployeeId()).build();
        });
    }
}
