package se.jocke.employee.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import se.jocke.api.EmployeeModel;
import se.jocke.employee.builder.EmployeeModelTestBuilder;

public class TestCreateEmployeeModel {
    private final EmployeeModel EMPLOYEE_MODEL_WITH_TESTBUILDER = EmployeeModelTestBuilder.builderMethod().build(); // använder TESTBUILDER

    @Test
    public void testCreateEmployeeModel() {
        EmployeeModel employeeModelWithBuilder = EmployeeModel.builder() // använder EMPLOYEEMODEL
                .employeeId(EMPLOYEE_MODEL_WITH_TESTBUILDER.getEmployeeId()) // fyller attributen med EM_W_TB:s attribut
                .firstName(EMPLOYEE_MODEL_WITH_TESTBUILDER.getFirstName())
                .lastName(EMPLOYEE_MODEL_WITH_TESTBUILDER.getLastName())
                .salary(EMPLOYEE_MODEL_WITH_TESTBUILDER.getSalary())
                .fullTime(EMPLOYEE_MODEL_WITH_TESTBUILDER.getFullTime())
                .departmentId(EMPLOYEE_MODEL_WITH_TESTBUILDER.getDepartmentId())
                .build();

        Assertions.assertEquals(EMPLOYEE_MODEL_WITH_TESTBUILDER.getEmployeeId(), employeeModelWithBuilder.getEmployeeId());
        Assertions.assertEquals(EMPLOYEE_MODEL_WITH_TESTBUILDER.getFirstName(), employeeModelWithBuilder.getFirstName());
        Assertions.assertEquals(EMPLOYEE_MODEL_WITH_TESTBUILDER.getLastName(), employeeModelWithBuilder.getLastName());
        Assertions.assertEquals(EMPLOYEE_MODEL_WITH_TESTBUILDER.getSalary(), employeeModelWithBuilder.getSalary());
        Assertions.assertEquals(EMPLOYEE_MODEL_WITH_TESTBUILDER.getFullTime(), employeeModelWithBuilder.getFullTime());
        Assertions.assertEquals(EMPLOYEE_MODEL_WITH_TESTBUILDER.getDepartmentId(), employeeModelWithBuilder.getDepartmentId());
        Assertions.assertEquals(EMPLOYEE_MODEL_WITH_TESTBUILDER, employeeModelWithBuilder);
    }

    @Test
    public void testCreateEmployeeModelThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            EmployeeModel.builder().firstName(EMPLOYEE_MODEL_WITH_TESTBUILDER.getFirstName()).build();
            // Varför blir du null?
        });
    }
}
