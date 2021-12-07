package se.jocke.employee.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.test.builder.EmployeeModelTestBuilder;

public class TestEmployeeModel {
    private static final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.bygg();

    @Test
    public void testThatEmployeeModelIsCreated(){
        EmployeeModel employeeModel = EmployeeModel.builder()
                .employeeId(EMPLOYEE_MODEL.getEmployeeId())
                .firstName(EMPLOYEE_MODEL.getFirstName())
                .lastName(EMPLOYEE_MODEL.getLastName())
                .fullTime(EMPLOYEE_MODEL.getFullTime())
                .departmentId(EMPLOYEE_MODEL.getDepartmentId())
                .salary(EMPLOYEE_MODEL.getSalary())
                .build();

        Assertions.assertEquals(EMPLOYEE_MODEL, employeeModel);
        Assertions.assertEquals(EMPLOYEE_MODEL.getFirstName(), employeeModel.getFirstName());
        Assertions.assertEquals(EMPLOYEE_MODEL.getLastName(), employeeModel.getLastName());
        Assertions.assertEquals(EMPLOYEE_MODEL.getEmployeeId(), employeeModel.getEmployeeId());
        Assertions.assertEquals(EMPLOYEE_MODEL.getFullTime(), employeeModel.getFullTime());
        Assertions.assertEquals(EMPLOYEE_MODEL.getDepartmentId(), employeeModel.getDepartmentId());
        Assertions.assertEquals(EMPLOYEE_MODEL.getSalary(), employeeModel.getSalary());
        Assertions.assertEquals(EMPLOYEE_MODEL.toString(), employeeModel.toString());
    }
}
