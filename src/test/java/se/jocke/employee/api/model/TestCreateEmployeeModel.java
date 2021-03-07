package se.jocke.employee.api.model;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import se.jocke.api.EmployeeModel;
import se.jocke.employee.builder.EmployeeModelTestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCreateEmployeeModel {

    private final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.builder().build();


    @Test
    public void testCreateEmployeeModel() {
        EmployeeModel employeeModel = EmployeeModel.builder()
                .employeeId(EMPLOYEE_MODEL.getEmployeeId())
                .departmentId(EMPLOYEE_MODEL.getDepartmentId())
                .salary(EMPLOYEE_MODEL.getSalary())
                .firstName(EMPLOYEE_MODEL.getFirstName())
                .lastName(EMPLOYEE_MODEL.getLastName())
                .fullTime(EMPLOYEE_MODEL.getFullTime())
                .build();

        Assertions.assertAll(
                () -> assertEquals(EMPLOYEE_MODEL.getEmployeeId(), employeeModel.getEmployeeId()),
                () -> assertEquals(EMPLOYEE_MODEL.getDepartmentId(), employeeModel.getDepartmentId()),
                () -> assertEquals(EMPLOYEE_MODEL.getSalary(), employeeModel.getSalary()),
                () -> assertEquals(EMPLOYEE_MODEL.getFirstName(), employeeModel.getFirstName()),
                () -> assertEquals(EMPLOYEE_MODEL.getLastName(), employeeModel.getLastName()),
                () -> assertEquals(EMPLOYEE_MODEL.getFullTime(), employeeModel.getFullTime())
        );

    }

    @Test
    public void testCreateEmployeeModelException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            EmployeeModel.builder().firstName(EMPLOYEE_MODEL.getFirstName()).build();
        });

    }


}
