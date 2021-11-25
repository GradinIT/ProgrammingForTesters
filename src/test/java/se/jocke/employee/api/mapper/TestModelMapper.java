package se.jocke.employee.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.test.builder.EmployeeModelTestBuilder;
import se.jocke.employee.test.builder.EmployeeTestBuilder;
import se.jocke.employee.entity.Employee;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestModelMapper {
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
    public void testEmployeeModelToEmployeeMapping() {
        Employee employee = EmployeeModelMapper.map(EMPLOYEE_MODEL);
        Assertions.assertAll(
                () -> assertEquals(EMPLOYEE_MODEL.getEmployeeId(), EMPLOYEE.getEmployeeId().getId()),
                () -> assertEquals(EMPLOYEE_MODEL.getFirstName(), EMPLOYEE.getFirstName()),
                () -> assertEquals(EMPLOYEE_MODEL.getLastName(), EMPLOYEE.getLastName()),
                () -> assertEquals(EMPLOYEE_MODEL.getSalary(), EMPLOYEE.getSalary()),
                () -> assertEquals(EMPLOYEE_MODEL.getFullTime(), EMPLOYEE.getFullTime()),
                () -> assertEquals(EMPLOYEE_MODEL.getDepartmentId(), EMPLOYEE.getDepartmentId())
        );
    }
}
