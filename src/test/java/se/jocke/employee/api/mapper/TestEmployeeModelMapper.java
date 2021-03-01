package se.jocke.employee.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.api.EmployeeModel;
import se.jocke.api.mapper.EmployeeModelMapper;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeModelTestBuilder;
import se.jocke.employee.builder.EmployeeTestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEmployeeModelMapper {
    private final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.builder().build();
    private final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();

    @Test
    public void testEmployeeToEmployeeModelMapping() {
        EmployeeModel model = EmployeeModelMapper.map(EMPLOYEE);
        Assertions.assertAll(
                () -> assertEquals(EMPLOYEE.getEmployeeId().getId(), model.getEmployeeId()),
                () -> assertEquals(EMPLOYEE.getFirstName(), model.getFirstName()),
                () -> assertEquals(EMPLOYEE.getLastName(), model.getLastName()),
                () -> assertEquals(EMPLOYEE.getSalary(), model.getSalary()),
                () -> assertEquals(EMPLOYEE.getFullTime(), model.getFullTime()),
                () -> assertEquals(EMPLOYEE.getDepartmentId(), model.getDepartmentId())
        );
    }

    @Test
    public void testEmployeeModelToDepartmentMapping() {
        Employee employee = EmployeeModelMapper.map(EMPLOYEE_MODEL);
        Assertions.assertAll(
                () -> assertEquals(EMPLOYEE.getEmployeeId().getId(), employee.getEmployeeId().getId()),
                () -> assertEquals(EMPLOYEE.getFirstName(), employee.getFirstName()),
                () -> assertEquals(EMPLOYEE.getLastName(), employee.getLastName()),
                () -> assertEquals(EMPLOYEE.getSalary(), employee.getSalary()),
                () -> assertEquals(EMPLOYEE.getFullTime(), employee.getFullTime()),
                () -> assertEquals(EMPLOYEE.getDepartmentId(), employee.getDepartmentId())

        );
    }
}
