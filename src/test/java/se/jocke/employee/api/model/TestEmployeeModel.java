package se.jocke.employee.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.department.api.DepartmentModel;
import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.builder.EmployeeModelTestBuilder;
import se.jocke.employee.api.EmployeeModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

        assertEquals(EMPLOYEE_MODEL.getFirstName(), employeeModel.getFirstName());
        assertEquals(EMPLOYEE_MODEL.getDepartmentId(), employeeModel.getDepartmentId());
        assertEquals(EMPLOYEE_MODEL.getEmployeeId(), employeeModel.getEmployeeId());
        assertEquals(EMPLOYEE_MODEL.getLastName(), employeeModel.getLastName());
        assertEquals(EMPLOYEE_MODEL.getSalary(), employeeModel.getSalary());
        assertEquals(EMPLOYEE_MODEL.getFullTime(), employeeModel.getFullTime());
        assertEquals(EMPLOYEE_MODEL,employeeModel);
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
