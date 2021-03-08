package se.jocke.employee.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.api.EmployeeModel;
import se.jocke.employee.builder.EmployeeModelTestBuilder;

public class TestCreateEmployeeModel { //passed 3/1 by default,

    private final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.builder().build();

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

    @Test //the original, keep this one
    public void testCreateEmployeeModelThrowsException () {
        Assertions.assertThrows(NullPointerException.class, () -> {
            EmployeeModel.builder().firstName(EMPLOYEE_MODEL.getFirstName()).build();
        });  //EmployeeModel.builder().firstName(EMPLOYEE_MODEL.getFirstName()) // for comparison
    }

   /* @Test //my own test to understand the purpose of the assertThrows test. Result: "Expected java.lang.NullPointerException to be thrown, but nothing was thrown".
    public void testCreateEmployeeModelThrowsException () {           // Conclusion: This test is to control that the EmployeeModel is given all parameters needed, if not
        Assertions.assertThrows(NullPointerException.class, () -> {     // an NullPointerException is thrown. EmployeeModels parameters all have the constraint of @NonNull.
            EmployeeModel.builder().employeeId(EMPLOYEE_MODEL.getEmployeeId())
                    .firstName(EMPLOYEE_MODEL.getFirstName())
                    .lastName(EMPLOYEE_MODEL.getLastName())
                    .salary(EMPLOYEE_MODEL.getSalary())
                    .fullTime(EMPLOYEE_MODEL.getFullTime())
                    .departmentId(EMPLOYEE_MODEL.getDepartmentId())
                    .build();
        });
    }*/

}
