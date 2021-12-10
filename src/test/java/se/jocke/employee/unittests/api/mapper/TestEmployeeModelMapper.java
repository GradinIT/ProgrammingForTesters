package se.jocke.employee.unittests.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.unittests.Builder.EmployeeModelTestBuilder;
import se.jocke.employee.unittests.Builder.EmployeeTestBuilder;
import se.jocke.employee.unittests.api.EmployeeModel;
import se.jocke.employee.unittests.entity.Employee;
import static org.junit.Assert.assertEquals;


public class TestEmployeeModelMapper {
    private final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.build();
    private final Employee EMPLOYEE = EmployeeTestBuilder.build();

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
                () -> assertEquals(EMPLOYEE_MODEL.getEmployeeId(), employee.getEmployeeId().getId()),
                () -> assertEquals(EMPLOYEE_MODEL.getFirstName(), employee.getFirstName()),
                () -> assertEquals(EMPLOYEE_MODEL.getLastName(), employee.getLastName()),
                () -> assertEquals(EMPLOYEE_MODEL.getSalary(), employee.getSalary()),
                () -> assertEquals(EMPLOYEE_MODEL.getFullTime(), employee.getFullTime()),
                () -> assertEquals(EMPLOYEE_MODEL.getDepartmentId(), employee.getDepartmentId())
        );
    }
}