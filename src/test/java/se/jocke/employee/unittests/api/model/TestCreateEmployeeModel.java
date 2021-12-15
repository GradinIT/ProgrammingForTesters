package se.jocke.employee.unittests.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.unittests.Builder.EmployeeModelTestBuilder;
import se.jocke.employee.unittests.api.EmployeeModel;

public class TestCreateEmployeeModel {
    private final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.build();


    @Test
    public void testCreateEmployeeModel() {
        EmployeeModel employee =EmployeeModel.builder()
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

    }
    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingEmployeeId() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            EmployeeModel.builder().firstName(EMPLOYEE_MODEL.getFirstName()).build();
            EmployeeModel.builder().lastName(EMPLOYEE_MODEL.getLastName()).build();
            EmployeeModel.builder().salary(EMPLOYEE_MODEL.getSalary()).build();
            EmployeeModel.builder().fullTime(EMPLOYEE_MODEL.getFullTime()).build();
            EmployeeModel.builder().departmentId(EMPLOYEE_MODEL.getDepartmentId()).build();
        });
    }

    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingEmployeeFirstName() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            EmployeeModel.builder().employeeId(EMPLOYEE_MODEL.getEmployeeId()).build();
            EmployeeModel.builder().lastName(EMPLOYEE_MODEL.getLastName()).build();
            EmployeeModel.builder().salary(EMPLOYEE_MODEL.getSalary()).build();
            EmployeeModel.builder().fullTime(EMPLOYEE_MODEL.getFullTime()).build();
            EmployeeModel.builder().departmentId(EMPLOYEE_MODEL.getDepartmentId()).build();
        });
    }

    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingEmployeeLastName() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            EmployeeModel.builder().employeeId(EMPLOYEE_MODEL.getEmployeeId()).build();
            EmployeeModel.builder().firstName(EMPLOYEE_MODEL.getFirstName()).build();
            EmployeeModel.builder().salary(EMPLOYEE_MODEL.getSalary()).build();
            EmployeeModel.builder().fullTime(EMPLOYEE_MODEL.getFullTime()).build();
            EmployeeModel.builder().departmentId(EMPLOYEE_MODEL.getDepartmentId()).build();
        });
    }

    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingFulltime() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            EmployeeModel.builder().employeeId(EMPLOYEE_MODEL.getEmployeeId()).build();
            EmployeeModel.builder().lastName(EMPLOYEE_MODEL.getLastName()).build();
            EmployeeModel.builder().salary(EMPLOYEE_MODEL.getSalary()).build();
            EmployeeModel.builder().firstName(EMPLOYEE_MODEL.getFirstName()).build();
            EmployeeModel.builder().departmentId(EMPLOYEE_MODEL.getDepartmentId()).build();
        });
    }

    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingSalary() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            EmployeeModel.builder().employeeId(EMPLOYEE_MODEL.getEmployeeId()).build();
            EmployeeModel.builder().lastName(EMPLOYEE_MODEL.getLastName()).build();
            EmployeeModel.builder().firstName(EMPLOYEE_MODEL.getFirstName()).build();
            EmployeeModel.builder().fullTime(EMPLOYEE_MODEL.getFullTime()).build();
            EmployeeModel.builder().departmentId(EMPLOYEE_MODEL.getDepartmentId()).build();
        });
    }

    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingEmployeeDepartmentId() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            EmployeeModel.builder().employeeId(EMPLOYEE_MODEL.getEmployeeId()).build();
            EmployeeModel.builder().lastName(EMPLOYEE_MODEL.getLastName()).build();
            EmployeeModel.builder().salary(EMPLOYEE_MODEL.getSalary()).build();
            EmployeeModel.builder().fullTime(EMPLOYEE_MODEL.getFullTime()).build();
            EmployeeModel.builder().firstName(EMPLOYEE_MODEL.getFirstName()).build();
        });
    }
}
