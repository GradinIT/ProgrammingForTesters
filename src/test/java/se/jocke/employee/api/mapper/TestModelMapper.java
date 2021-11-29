package se.jocke.employee.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.builder.EmployeeModelTestBuilder;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.employee.entity.Employee;

public class TestModelMapper {
    private final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.builder().build();
    private final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();

    @Test
    public void testEmployeeToEmployeeModelMapping() {
        EmployeeModel model = EmployeeModelMapper.map(EMPLOYEE);
        Assertions.assertAll(
                () -> Assertions.assertEquals(EMPLOYEE.getEmployeeId().getId(), model.getEmployeeId()),
                () -> Assertions.assertEquals(EMPLOYEE.getFirstName(), model.getFirstName()),
                () -> Assertions.assertEquals(EMPLOYEE.getLastName(), model.getLastName()),
                () -> Assertions.assertEquals(EMPLOYEE.getSalary(), model.getSalary()),
                () -> Assertions.assertEquals(EMPLOYEE.getFullTime(), model.getFullTime()),
                () -> Assertions.assertEquals(EMPLOYEE.getDepartmentId(), model.getDepartmentId()));
    }

    @Test
    public void testEmployeeModelToEmployeeMapping() {
        Employee employee = EmployeeModelMapper.map(EMPLOYEE_MODEL);
        Assertions.assertAll(
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getEmployeeId(), employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getFirstName(), employee.getFirstName()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getLastName(), employee.getLastName()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getSalary(), employee.getSalary()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getFullTime(), employee.getFullTime()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getDepartmentId(), employee.getDepartmentId()));
    }
}