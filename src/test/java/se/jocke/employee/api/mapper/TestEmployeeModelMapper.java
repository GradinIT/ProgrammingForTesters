package se.jocke.employee.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.entity.EmployeeTestBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEmployeeModelMapper {
    private final Employee EMPLOYEE = EmployeeTestBuilder.build();
    private final EmployeeModel EMPLOYEE_MODEL = EmployeeModel.builder()
            .firstName(EMPLOYEE.getFirstName())
            .lastName(EMPLOYEE.getLastName())
            .fullTime(EMPLOYEE.getFullTime())
            .salary(EMPLOYEE.getSalary())
            .departmentId(EMPLOYEE.getDepartmentId())
            .employeeId(EMPLOYEE.getEmployeeId().getId())
            .build();

    @Test
    public void testDepartmentToDepartmentModelMapping() {
        EmployeeModel model = EmployeeModelMapper.map(EMPLOYEE);
        Assertions.assertAll(
                () -> assertEquals(EMPLOYEE.getEmployeeId().getId(), model.getEmployeeId()),
                () -> assertEquals(EMPLOYEE.getFirstName(), model.getFirstName()),
                () -> assertEquals(EMPLOYEE.getLastName(), model.getLastName()),
                () -> assertEquals(EMPLOYEE.getSalary(), model.getSalary()),
                () -> assertEquals(EMPLOYEE.getDepartmentId(), model.getDepartmentId())

        );
    }

    @Test
    public void testDepartmentModelToDepartmentMapping() {
        Employee employee = EmployeeModelMapper.map(EMPLOYEE_MODEL);
       Assertions.assertAll(
              () -> assertEquals(EMPLOYEE_MODEL.getEmployeeId(), employee.getEmployeeId()),
               () -> assertEquals(EMPLOYEE_MODEL.getFirstName(), employee.getFirstName())

       );
    }
}
