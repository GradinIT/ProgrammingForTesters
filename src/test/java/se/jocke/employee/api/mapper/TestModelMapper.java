package se.jocke.employee.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.api.EmployeeModel;
import se.jocke.api.mapper.EmployeeModelMapper;
import se.jocke.employee.builder.EmployeeModelTestBuilder;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.department.entity.Employee;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestModelMapper {
    private final Employee EMPLOYEE_MODEL = EmployeeTestBuilder.builder().build();
    private final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();

    @Test
    public void testEmployeeToEmployeeModelMapping() {
        EmployeeModel model = EmployeeModelMapper.map(EMPLOYEE);
        Assertions.assertAll(
                () -> assertEquals(EMPLOYEE.getEmployeeId().getId(), model.getEmployeeId()),
                () -> assertEquals(EMPLOYEE.getFirstName(), model.getFirstName()),
                () -> assertEquals(EMPLOYEE.getLastName(), model.getLastName()),
                () -> assertEquals(EMPLOYEE.getFullTime(), model.getFullTime()),
                () -> assertEquals(EMPLOYEE.getSalary(), model.getSalary())
        );
    }

    @Test
    public void testEmployeeModelToDepartmentMapping() {
        EmployeeModel model = EmployeeModelMapper.map(EMPLOYEE_MODEL);
        Assertions.assertAll(
                () -> assertEquals(EMPLOYEE_MODEL.getEmployeeId().getId(), model.getEmployeeId()),
                () -> assertEquals(EMPLOYEE_MODEL.getFirstName(), model.getFirstName()),
                () -> assertEquals(EMPLOYEE_MODEL.getLastName(), model.getLastName()),
                () -> assertEquals(EMPLOYEE_MODEL.getFullTime(), model.getFullTime()),
                () -> assertEquals(EMPLOYEE_MODEL.getSalary(), model.getSalary())
        );
    }
}
