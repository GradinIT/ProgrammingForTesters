package se.jocke.employee.unittest.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.api.mapper.EmployeeModelMapper;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.test.builder.EmployeeTestBuilder;

public class TestEmployeeModelMapper {
    private static final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();
    private static final EmployeeModel EMPLOYEE_MODEL = EmployeeModel.builder()
            .employeeId(EMPLOYEE.getEmployeeId().getId())
            .firstName(EMPLOYEE.getFirstName())
            .lastName(EMPLOYEE.getLastName())
            .fullTime(EMPLOYEE.getFullTime())
            .salary(EMPLOYEE.getSalary())
            .departmentId(EMPLOYEE.getDepartmentId())
            .build();

    @Test
    public void testThatEmployeeModelIsEqualToEmployee() {
        EmployeeModel employeeModel = EmployeeModelMapper.map(EMPLOYEE);
        Assertions.assertEquals(EMPLOYEE_MODEL,employeeModel);
        Assertions.assertEquals(EMPLOYEE.getFirstName(),employeeModel.getFirstName());
        Assertions.assertEquals(EMPLOYEE.getEmployeeId().getId(),employeeModel.getEmployeeId());
    }
    @Test
    public void testThatEmployeeIsEqualToEmployeeModel() {
        Employee employee = EmployeeModelMapper.map(EMPLOYEE_MODEL);
        Assertions.assertEquals(EMPLOYEE,employee);
        Assertions.assertEquals(EMPLOYEE_MODEL.getFirstName(),employee.getFirstName());
        Assertions.assertEquals(EMPLOYEE_MODEL.getEmployeeId(),employee.getEmployeeId().getId());
    }
}
