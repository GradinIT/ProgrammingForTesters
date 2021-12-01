package se.jocke.employee.unittest.api.model;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import se.jocke.employee.builder.EmployeeModelTestBuilder;
import se.jocke.employee.unittest.api.EmployeeModel;

public class TestEmployeeModel {
    private static final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.build();

    @Test
    public void testThatEmployeeIsCreated(){
        EmployeeModel employeeModel = EmployeeModel.builder()
                .employeeId(EMPLOYEE_MODEL.getEmployeeId())
                .departmentId(EMPLOYEE_MODEL.getDepartmentId())
                .fullTime(EMPLOYEE_MODEL.getFullTime())
                .salary(EMPLOYEE_MODEL.getSalary())
                .firstName(EMPLOYEE_MODEL.getFirstName())
                .lastName(EMPLOYEE_MODEL.getLastName())
                .build();

        Assertions.assertEquals(EMPLOYEE_MODEL,employeeModel);

        Assertions.assertEquals(EMPLOYEE_MODEL.getEmployeeId(),employeeModel.getEmployeeId());
        Assertions.assertEquals(EMPLOYEE_MODEL.getDepartmentId(),employeeModel.getDepartmentId());
        Assertions.assertEquals(EMPLOYEE_MODEL.getFullTime(),employeeModel.getFullTime());
        Assertions.assertEquals(EMPLOYEE_MODEL.getSalary(),employeeModel.getSalary());
        Assertions.assertEquals(EMPLOYEE_MODEL.getFirstName(),employeeModel.getFirstName());
        Assertions.assertEquals(EMPLOYEE_MODEL.getLastName(),employeeModel.getLastName());

        Assertions.assertEquals(EMPLOYEE_MODEL.toString(),employeeModel.toString());
    }

    @Test
    public void testThatNullPointerExceptionWorksWhenNotGivingFirstname() {
        Assertions.assertThrows(NullPointerException.class, () -> EmployeeModel.builder()
                .salary(EMPLOYEE_MODEL.getSalary())
                .fullTime(EMPLOYEE_MODEL.getFullTime())
                .departmentId(EMPLOYEE_MODEL.getDepartmentId())
                .employeeId(EMPLOYEE_MODEL.getEmployeeId())
                .lastName(EMPLOYEE_MODEL.getLastName())
                //.firstName(EMPLOYEE_MODEL.getFirstName())
                .build());
    }
    @Test
    public void testThatNullPointerExceptionWorksWhenNotGivingEmployeeId() {
        Assertions.assertThrows(NullPointerException.class, () -> EmployeeModel.builder()
                .salary(EMPLOYEE_MODEL.getSalary())
                .fullTime(EMPLOYEE_MODEL.getFullTime())
                .departmentId(EMPLOYEE_MODEL.getDepartmentId())
                //.employeeId(EMPLOYEE_MODEL.getEmployeeId())
                .lastName(EMPLOYEE_MODEL.getLastName())
                .firstName(EMPLOYEE_MODEL.getFirstName())
                .build());
    }

    @Test
    public void testThatNullPointerExceptionWorksWhenNotGivingSalary() {
        Assertions.assertThrows(NullPointerException.class, () -> EmployeeModel.builder()
                //.salary(EMPLOYEE_MODEL.getSalary())
                .fullTime(EMPLOYEE_MODEL.getFullTime())
                .departmentId(EMPLOYEE_MODEL.getDepartmentId())
                .employeeId(EMPLOYEE_MODEL.getEmployeeId())
                .lastName(EMPLOYEE_MODEL.getLastName())
                .firstName(EMPLOYEE_MODEL.getFirstName())
                .build());
    }

}
