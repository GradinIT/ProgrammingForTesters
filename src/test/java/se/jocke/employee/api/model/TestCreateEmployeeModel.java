package se.jocke.employee.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import se.jocke.api.EmployeeModel;
import se.jocke.api.mapper.EmployeeModelMapper;
import se.jocke.employee.builder.EmployeeModelTestBuilder;

public class TestCreateEmployeeModel {
    private final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.builder().build();

    @Test
    public void testCreateEmployeeModel() {
        EmployeeModel employeeModel = EmployeeModelMapper.mapEmployeeModel(EMPLOYEE_MODEL);

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
    public void testCreateEmployeeModelThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () ->
                EmployeeModel.builder().firstName(EMPLOYEE_MODEL.getFirstName()).build());
    }
}
