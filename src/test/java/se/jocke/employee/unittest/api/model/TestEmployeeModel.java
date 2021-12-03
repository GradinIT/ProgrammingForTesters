package se.jocke.employee.unittest.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.test.builder.EmployeeModelTestBuilder;

public class TestEmployeeModel {
    private static final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.build();

    @Test
    public void testThatEmployeeIsCreated() {
        EmployeeModel employeeModel = EmployeeModel.builder()
                .departmentId(EMPLOYEE_MODEL.getDepartmentId())
                .employeeId(EMPLOYEE_MODEL.getEmployeeId())
                .salary(EMPLOYEE_MODEL.getSalary())
                .fullTime(EMPLOYEE_MODEL.getFullTime())
                .firstName(EMPLOYEE_MODEL.getFirstName())
                .lastName(EMPLOYEE_MODEL.getLastName())
                .build();

        Assertions.assertEquals(EMPLOYEE_MODEL, employeeModel);
        Assertions.assertEquals(EMPLOYEE_MODEL.getDepartmentId(), employeeModel.getDepartmentId());
        Assertions.assertEquals(EMPLOYEE_MODEL.getEmployeeId(), employeeModel.getEmployeeId());
        Assertions.assertEquals(EMPLOYEE_MODEL.getFirstName(),employeeModel.getFirstName());
        Assertions.assertEquals(EMPLOYEE_MODEL.getLastName(), employeeModel.getLastName());
        Assertions.assertEquals(EMPLOYEE_MODEL.getFullTime(), employeeModel.getFullTime());
        Assertions.assertEquals(EMPLOYEE_MODEL.getSalary(), employeeModel.getSalary());
        Assertions.assertEquals(EMPLOYEE_MODEL.toString(), employeeModel.toString());
    }

    @Test
    public void testThatNullPointerExceptionIsRaisedWhenOnlyProvidingDepartmentId() {
        Assertions.assertThrows(NullPointerException.class,
                () -> EmployeeModel.builder().departmentId(EMPLOYEE_MODEL.getDepartmentId()).build());

    }

    @Test
    public void testThatNullPointerExceptionIsRaisedWhenOnlyProvidingFirstName() {
        Assertions.assertThrows(NullPointerException.class,
                () -> EmployeeModel.builder().firstName(EMPLOYEE_MODEL.getFirstName()).build());

    }
}
