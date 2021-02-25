package se.jocke.employee.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.api.EmployeeModel;
import se.jocke.employee.builder.EmployeeModelTestBuilder;

public class TestCreateEmployeeModel {
    private final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.builder().build();


    @Test
    public void testCreateEmployeeModel() {
        EmployeeModel employee = EmployeeModel.builder()
                .employeeId(EMPLOYEE_MODEL.getEmployeeId())
                .firstName(EMPLOYEE_MODEL.getFirstName())
                .build();

        Assertions.assertEquals(EMPLOYEE_MODEL.getFirstName(), employee.getFirstName());
        Assertions.assertEquals(EMPLOYEE_MODEL.getDepartmentId(), employee.getEmployeeId());
        Assertions.assertEquals(EMPLOYEE_MODEL,employee);
    }

    @Test
    public void testCreateEmployeeModelThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            EmployeeModel.builder().firstName(EMPLOYEE_MODEL.getFirstName()).build();
        });
    }
}
