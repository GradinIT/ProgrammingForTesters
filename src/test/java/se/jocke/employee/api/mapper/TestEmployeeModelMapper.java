package se.jocke.employee.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.employee.entity.Employee;

public class TestEmployeeModelMapper {
    private static final Employee EMPLOYEE = EmployeeTestBuilder.build();
    private static final EmployeeModel EMPLOYEE_MODEL = EmployeeModel.builder()
            .departmentId(EMPLOYEE.getDepartmentId())
            .firstName(EMPLOYEE.getFirstName())
            .lastName(EMPLOYEE.getLastName())
            .salary(EMPLOYEE.getSalary())
            .fullTime(EMPLOYEE.getFullTime())
            .employeeId(EMPLOYEE.getEmployeeId())
            .build();

    @Test
    public void testThatEmployeeModelIsEqualToEmployee() {
        EmployeeModel employeeModel = EmployeeModelMapper.map(EMPLOYEE);
        Assertions.assertEquals(EMPLOYEE_MODEL,employeeModel);
        Assertions.assertEquals(EMPLOYEE.getFirstName(),employeeModel.getFirstName());
        Assertions.assertEquals(EMPLOYEE.getDepartmentId(),employeeModel.getDepartmentId());
        Assertions.assertEquals(EMPLOYEE.getLastName(), employeeModel.getLastName());
        Assertions.assertEquals(EMPLOYEE.getSalary(), employeeModel.getSalary());
        Assertions.assertEquals(EMPLOYEE.getEmployeeId(), employeeModel.getEmployeeId());
    }

    @Test
    public void testThatEmployeeIsEqualToEmployeeModel() {
        Employee employee = EmployeeModelMapper.map(EMPLOYEE_MODEL);
        Assertions.assertEquals(EMPLOYEE, employee);
        Assertions.assertEquals(EMPLOYEE_MODEL.getDepartmentId(),employee.getDepartmentId());
        Assertions.assertEquals(EMPLOYEE_MODEL.getFirstName(),employee.getFirstName());
        Assertions.assertEquals(EMPLOYEE_MODEL.getLastName(),employee.getLastName());
        Assertions.assertEquals(EMPLOYEE_MODEL.getEmployeeId(),employee.getEmployeeId());
        Assertions.assertEquals(EMPLOYEE_MODEL.getFullTime(),employee.getFullTime());
        Assertions.assertEquals(EMPLOYEE_MODEL.getSalary(), employee.getSalary());
    }
}
