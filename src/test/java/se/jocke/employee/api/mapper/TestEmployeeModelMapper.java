package se.jocke.employee.api.mapper;

import io.cucumber.java.bs.A;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.test.builder.EmployeeTestBuilder;

public class TestEmployeeModelMapper {
    private static final Employee EMPLOYEE = EmployeeTestBuilder.bygg();
    private static final EmployeeModel EMPLOYEE_MODEL = EmployeeModel.builder()
            .employeeId(EMPLOYEE.getEmployeeId().getId())
            .firstName(EMPLOYEE.getFirstName())
            .lastName(EMPLOYEE.getLastName())
            .salary(EMPLOYEE.getSalary())
            .fullTime(EMPLOYEE.getFullTime())
            .departmentId(EMPLOYEE.getDepartmentId())
            .build();


    @Test
    public void testThatEmployeeModelIsEqualToEmployee() {
        EmployeeModel employeeModel = EmployeeModelMapper.map(EMPLOYEE);
        Assertions.assertEquals(EMPLOYEE_MODEL, employeeModel);
        Assertions.assertEquals(EMPLOYEE.getFirstName(), employeeModel.getFirstName());
        Assertions.assertEquals(EMPLOYEE.getLastName(), employeeModel.getLastName());
        Assertions.assertEquals(EMPLOYEE.getEmployeeId().getId(), employeeModel.getEmployeeId());
        Assertions.assertEquals(EMPLOYEE.getSalary(), employeeModel.getSalary());
        Assertions.assertEquals(EMPLOYEE.getFullTime(), employeeModel.getFullTime());
        Assertions.assertEquals(EMPLOYEE.getDepartmentId(), employeeModel.getDepartmentId());
    }

    @Test
    public void testThatEmployeeIsEqualToEmployeeModel(){
        Employee employee = EmployeeModelMapper.map(EMPLOYEE_MODEL);
        Assertions.assertEquals(EMPLOYEE, employee);
        Assertions.assertEquals(EMPLOYEE_MODEL.getFirstName(), employee.getFirstName());
        Assertions.assertEquals(EMPLOYEE_MODEL.getLastName(), employee.getLastName());
        Assertions.assertEquals(EMPLOYEE_MODEL.getEmployeeId(), employee.getEmployeeId().getId());
        Assertions.assertEquals(EMPLOYEE_MODEL.getSalary(), employee.getSalary());
        Assertions.assertEquals(EMPLOYEE_MODEL.getFullTime(), employee.getFullTime());
        Assertions.assertEquals(EMPLOYEE_MODEL.getDepartmentId(), employee.getDepartmentId());
    }
}

