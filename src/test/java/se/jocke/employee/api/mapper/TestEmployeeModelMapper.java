
package se.jocke.employee.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import se.jocke.api.DepartmentModel;
import se.jocke.api.EmployeeModel;
import se.jocke.api.mapper.DepartmentModelMapper;
import se.jocke.api.mapper.EmployeeModelMapper;
import se.jocke.department.builder.DepartmentModelTestBuilder;
import se.jocke.department.builder.DepartmentTestBuilder;
import se.jocke.department.entity.Department;
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
                () -> Assertions.assertEquals(EMPLOYEE.getEmployeeId().getId(), model.getEmployeeId()),
                () -> Assertions.assertEquals(EMPLOYEE.getSalary(), model.getSalary()),
                () -> Assertions.assertEquals(EMPLOYEE.getFirstName(), model.getFirstName()),
                () -> Assertions.assertEquals(EMPLOYEE.getLastName(), model.getLastName()),
                () -> Assertions.assertEquals(EMPLOYEE.getFullTime(), model.getFullTime())
        );
    }

    @Test
    public void testEmployeeModelToEmployeeMapping() {
        Employee employee = EmployeeModelMapper.map(EMPLOYEE_MODEL);
        Assertions.assertAll(
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getEmployeeId(), employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getSalary(), employee.getSalary()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getFirstName(), employee.getFirstName()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getLastName(), employee.getLastName()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getFullTime(), employee.getFullTime())
        );

    }
}

