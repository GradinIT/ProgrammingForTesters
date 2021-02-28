package employeeTester;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.api.EmployeeModel;
import se.jocke.api.mapper.EmployeeModelMapper;
import se.jocke.employee.builder.EmployeeModelTestBuilder;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.department.entity.Employee;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestModelMapper {
    private final EmployeeModel Employee_MODEL = EmployeeModelTestBuilder.builder().build();
    private final Employee Employee = EmployeeTestBuilder.builder().build();

    @Test
    public void testEmployeeToEmployeeModelMapping() {
        EmployeeModel model = EmployeeModelMapper.map(Employee);
        Assertions.assertAll(
                () -> assertEquals(Employee.getEmployeeId().getId(), model.getEmployeeId()),
                () -> assertEquals(Employee.getFirstName(), model.getFirstName()),
                () -> assertEquals(Employee.getFullTime(), model.getFullTime()),
                () -> assertEquals(Employee.getSalary(), model.getSalary()),
                () -> assertEquals(Employee.getFullTime(), model.getFullTime()),
                () -> assertEquals(Employee.getDepartmentId(), model.getDepartmentId())
        );
    }

    @Test
    public void testEmployeeModelToEmployeeMapping() {
        Employee employee = EmployeeModelMapper.map(Employee_MODEL);
        Assertions.assertAll(
                () -> assertEquals(Employee_MODEL.getEmployeeId(), employee.getEmployeeId().getId()),
                () -> assertEquals(Employee_MODEL.getFirstName(), employee.getFirstName()),
                () -> assertEquals(Employee_MODEL.getFullTime(), employee.getFullTime()),
                () -> assertEquals(Employee_MODEL.getSalary(), employee.getSalary()),
                () -> assertEquals(Employee_MODEL.getFullTime(), employee.getFullTime()),
                () -> assertEquals(Employee_MODEL.getDepartmentId(), employee.getDepartmentId())


        );
    }
}


