package se.jocke.employee.api.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.jocke.api.EmployeeModel;
import se.jocke.api.mapper.EmployeeModelMapper;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeModelTestBuilder;
import se.jocke.employee.builder.EmployeeTestBuilder;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test EmployeeModelMapper")
public class TestModelMapper {

    private final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.builder().build();
    private final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();

    @Test
    @DisplayName("When we want to map employee to model")
    public void testEmployeeToEmployeeModelMapping() {
        EmployeeModel empModel = EmployeeModelMapper.map(EMPLOYEE);
        assertAll(
                () -> assertEquals(EMPLOYEE.getEmployeeId().getId() , empModel.getEmployeeId()),
                () -> assertEquals(EMPLOYEE.getFirstName(), empModel.getFirstName()),
                () -> assertEquals(EMPLOYEE.getLastName(), empModel.getLastName()),
                () -> assertEquals(EMPLOYEE.getSalary(), empModel.getSalary()),
                () -> assertEquals(EMPLOYEE.getFullTime(), empModel.getFullTime()),
                () -> assertEquals(EMPLOYEE.getDepartmentId(), empModel.getDepartmentId())
        );
    }

    @Test
    @DisplayName("When we want to map model to employee")
    public void testEmployeeModelToEmployeeMapping() {
        Employee empFromModel = EmployeeModelMapper.map(EMPLOYEE_MODEL);
        assertAll(
                () -> assertEquals(EMPLOYEE_MODEL.getEmployeeId(), empFromModel.getEmployeeId().getId()),
                () -> assertEquals(EMPLOYEE_MODEL.getFirstName(), empFromModel.getFirstName()),
                () -> assertEquals(EMPLOYEE_MODEL.getLastName(), empFromModel.getLastName()),
                () -> assertEquals(EMPLOYEE_MODEL.getSalary(), empFromModel.getSalary()),
                () -> assertEquals(EMPLOYEE_MODEL.getFullTime(), empFromModel.getFullTime()),
                () -> assertEquals(EMPLOYEE_MODEL.getDepartmentId(), empFromModel.getDepartmentId())
        );
    }

}
