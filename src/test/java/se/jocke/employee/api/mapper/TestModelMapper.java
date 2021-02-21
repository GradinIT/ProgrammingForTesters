package se.jocke.employee.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import se.jocke.api.EmployeeModel;
import se.jocke.api.mapper.EmployeeModelMapper;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeModelTestBuilder;
import se.jocke.employee.builder.EmployeeTestBuilder;
import static org.junit.Assert.assertEquals;

public class TestModelMapper {

    private final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();
    private final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.builder().build();

    @Test
    public void testEmployeeToEmployeeModel() {
        EmployeeModel model = EmployeeModelMapper.map(EMPLOYEE);
        Assertions.assertAll(
                () -> Assertions.assertEquals(EMPLOYEE.getDepartmentId(),model.getDepartmentId()),
                () -> Assertions.assertEquals(EMPLOYEE.getFirstName(),model.getFirstName())
        );
    }

    @Test
    public void testEmployeeModelToEmployee() {
        Employee employee = EmployeeModelMapper.map(EMPLOYEE_MODEL);
        Assertions.assertAll(
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getDepartmentId(),employee.getDepartmentId()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getFirstName(),employee.getFirstName())
        );
    }
}
