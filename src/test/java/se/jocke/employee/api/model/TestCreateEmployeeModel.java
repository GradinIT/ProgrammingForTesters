package se.jocke.employee.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.api.EmployeeModel;
import se.jocke.employee.builder.EmployeeModelTestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        assertEquals(EMPLOYEE_MODEL_WITH_TESTBUILDER.getEmployeeId(), employeeModelWithBuilder.getEmployeeId());
        assertEquals(EMPLOYEE_MODEL_WITH_TESTBUILDER.getFirstName(), employeeModelWithBuilder.getFirstName());
        assertEquals(EMPLOYEE_MODEL_WITH_TESTBUILDER.getLastName(), employeeModelWithBuilder.getLastName());
        assertEquals(EMPLOYEE_MODEL_WITH_TESTBUILDER.getSalary(), employeeModelWithBuilder.getSalary());
        assertEquals(EMPLOYEE_MODEL_WITH_TESTBUILDER.getFullTime(), employeeModelWithBuilder.getFullTime());
        assertEquals(EMPLOYEE_MODEL_WITH_TESTBUILDER.getDepartmentId(), employeeModelWithBuilder.getDepartmentId());
        assertEquals(EMPLOYEE_MODEL_WITH_TESTBUILDER, employeeModelWithBuilder);
    }

    @Test
    public void testCreateEmployeeModelThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () ->
            EmployeeModel.builder().firstName(EMPLOYEE_MODEL_WITH_TESTBUILDER.getFirstName()).build()
        );
    }
}
