package se.jocke.employee.api.model;

import io.cucumber.java.bs.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.api.EmployeeModel;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;

public class TestCreateEmployeeModel {

    private final Employee EMPLOYEE_MODEL = EmployeeTestBuilder.builder().build();

    @Test
    public void testBuildEmployeeModel() {
        Employee empModel = Employee.builder()
                .employeeId(EMPLOYEE_MODEL.getEmployeeId())
                .firstName(EMPLOYEE_MODEL.getFirstName())
                .lastName(EMPLOYEE_MODEL.getLastName())
                .fullTime(EMPLOYEE_MODEL.getFullTime())
                .salary(EMPLOYEE_MODEL.getSalary())
                .departmentId(EMPLOYEE_MODEL.getDepartmentId())
                .build();

                Assertions.assertEquals(EMPLOYEE_MODEL.getEmployeeId(),empModel.getEmployeeId());
                Assertions.assertEquals(EMPLOYEE_MODEL.getFirstName(),empModel.getFirstName());
                Assertions.assertEquals(EMPLOYEE_MODEL.getLastName(),empModel.getLastName());
                Assertions.assertEquals(EMPLOYEE_MODEL.getFullTime(),empModel.getFullTime());
                Assertions.assertEquals(EMPLOYEE_MODEL.getSalary(),empModel.getSalary());
                Assertions.assertEquals(EMPLOYEE_MODEL.getDepartmentId(),empModel.getDepartmentId());
                Assertions.assertEquals(EMPLOYEE_MODEL,empModel);
    }
}
