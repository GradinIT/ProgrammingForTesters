package se.jocke.employee.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.api.EmployeeModel;
import se.jocke.employee.builder.EmployeeModelTestBuilder;

/**
 * Created by Oscar Bergström 01-03-2021
 */
public class TestCreateEmployeeModel {

    // Create a dummy EmployeeModel to test against
    private final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.builder().build();

    // Create an Employee from the dummy model and then check that it's fields equal the models
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
        Assertions.assertEquals(EMPLOYEE_MODEL.getDepartmentId(), employee.getDepartmentId());
        Assertions.assertEquals(EMPLOYEE_MODEL, employee);
    }

    // Test that the EmployeeModel builder throws an exception if it does not get all fields it needs (I think?)
    @Test
    public void testCreateEmployeeModelThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> EmployeeModel.builder().firstName(EMPLOYEE_MODEL.getFirstName()).build());
    }

}
