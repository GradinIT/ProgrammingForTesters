package se.jocke.employee.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.jocke.api.EmployeeModel;
import se.jocke.api.mapper.EmployeeModelMapper;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeModelTestBuilder;
import se.jocke.employee.builder.EmployeeTestBuilder;


public class TestModelMapper {

    private final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.builder().build();
    private final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();

    @Test
    @DisplayName("When we map employee to model")
    public void testEmployeeToEmployeeModelMapping() {
        EmployeeModel empModel = EmployeeModelMapper.map(EMPLOYEE);
        Assertions.assertAll(
                () -> Assertions.assertEquals(EMPLOYEE.getEmployeeId().getId() , empModel.getEmployeeId()),
                () -> Assertions.assertEquals(EMPLOYEE.getFirstName(), empModel.getFirstName()),
                () -> Assertions.assertEquals(EMPLOYEE.getLastName(), empModel.getLastName()),
                () -> Assertions.assertEquals(EMPLOYEE.getSalary(), empModel.getSalary()),
                () -> Assertions.assertEquals(EMPLOYEE.getFullTime(), empModel.getFullTime()),
                () -> Assertions.assertEquals(EMPLOYEE.getDepartmentId(), empModel.getDepartmentId())
        );
    }

    @Test
    @DisplayName("When we map model to employee")
    public void testEmployeeModelToEmployeeMapping() {
        Employee empFromModel = EmployeeModelMapper.map(EMPLOYEE_MODEL);
        Assertions.assertAll(
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getEmployeeId(), empFromModel.getEmployeeId().getId()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getFirstName(), empFromModel.getFirstName()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getLastName(), empFromModel.getLastName()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getSalary(), empFromModel.getSalary()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getFullTime(), empFromModel.getFullTime()),
                () -> Assertions.assertEquals(EMPLOYEE_MODEL.getDepartmentId(), empFromModel.getDepartmentId())
        );
    }

}
