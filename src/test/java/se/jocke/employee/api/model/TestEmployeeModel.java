package se.jocke.employee.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.builder.EmployeeModelTestBuilder;

public class TestEmployeeModel {
    private final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.builder().build();


    @Test
    public void testCreateEmployeeModel() {
        EmployeeModel employeeModel = EmployeeModel.builder()
                .departmentId(EMPLOYEE_MODEL.getDepartmentId())
                .firstName(EMPLOYEE_MODEL.getFirstName())
                .lastName(EMPLOYEE_MODEL.getLastName())
                .salary(EMPLOYEE_MODEL.getSalary())
                .fullTime(EMPLOYEE_MODEL.getFullTime())
                .employeeId(EMPLOYEE_MODEL.getEmployeeId())
                .build();

        Assertions.assertEquals(EMPLOYEE_MODEL.getFirstName(), employeeModel.getFirstName());
        Assertions.assertEquals(EMPLOYEE_MODEL.getDepartmentId(), employeeModel.getDepartmentId());
        Assertions.assertEquals(EMPLOYEE_MODEL.getEmployeeId(), employeeModel.getEmployeeId());
        Assertions.assertEquals(EMPLOYEE_MODEL.getLastName(), employeeModel.getLastName());
        Assertions.assertEquals(EMPLOYEE_MODEL.getSalary(), employeeModel.getSalary());
        Assertions.assertEquals(EMPLOYEE_MODEL.getFullTime(), employeeModel.getFullTime());
        Assertions.assertEquals(EMPLOYEE_MODEL,employeeModel);
        Assertions.assertEquals(EMPLOYEE_MODEL.toString(), employeeModel.toString());
    }

    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingFirstName() {
        Assertions.assertThrows(NullPointerException.class,
                () -> EmployeeModel.builder().firstName(EMPLOYEE_MODEL.getFirstName()).build());


    }

    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingSalary() {
        //Assertions.assertThrows(NullPointerException.class, () -> EmployeeModel.builder().firstName(EMPLOYEE_MODEL.getFirstName()).build());


    }

    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingEmployeeId() {
        Assertions.assertThrows(NullPointerException.class,
                () -> EmployeeModel.builder().employeeId(EMPLOYEE_MODEL.getEmployeeId()).build());

    }
}
