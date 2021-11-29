package se.jocke.employee.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.builder.EmployeeModelTestBuilder;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.employee.entity.Employee;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestEmployeeModelMapper {
    private static final Employee EMPLOYEE = EmployeeTestBuilder.build();
    private static final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.build();
    /* EmployeeModel.builder()  //EmployeeModelTestbuilder.build() är samma som dessa rader
            .departmentId(EMPLOYEE.getDepartmentId())
            .firstName(EMPLOYEE.getFirstName())
            .lastName(EMPLOYEE.getLastName())
            .salary(EMPLOYEE.getSalary())
            .fullTime(EMPLOYEE.getFullTime())
            .employeeId(EMPLOYEE.getEmployeeId())
            .build();
            */


    @Test
    public void testThatEmployeeModelIsEqualToEmployee() {
        EmployeeModel employeeModel = EmployeeModelMapper.map(EMPLOYEE);
        assertEquals(EMPLOYEE_MODEL, employeeModel);
        assertEquals(EMPLOYEE.getFirstName(), employeeModel.getFirstName());
        assertEquals(EMPLOYEE.getDepartmentId(), employeeModel.getDepartmentId());
        assertEquals(EMPLOYEE.getLastName(), employeeModel.getLastName());
        assertEquals(EMPLOYEE.getSalary(), employeeModel.getSalary());
        assertEquals(EMPLOYEE.getEmployeeId(), employeeModel.getEmployeeId());
    }

    @Test
    public void testThatEmployeeIsEqualToEmployeeModel() {
        Employee employee = EmployeeModelMapper.map(EMPLOYEE_MODEL);
        assertEquals(EMPLOYEE, employee);
        assertEquals(EMPLOYEE_MODEL.getDepartmentId(), employee.getDepartmentId());
        assertEquals(EMPLOYEE_MODEL.getFirstName(), employee.getFirstName());
        assertEquals(EMPLOYEE_MODEL.getLastName(), employee.getLastName());
        assertEquals(EMPLOYEE_MODEL.getEmployeeId(), employee.getEmployeeId());
        assertEquals(EMPLOYEE_MODEL.getFullTime(), employee.getFullTime());
        assertEquals(EMPLOYEE_MODEL.getSalary(), employee.getSalary());
    }

    @Test
    public void testEmployeeToEmployeeModelMapping() {
        EmployeeModel model = EmployeeModelMapper.map(EMPLOYEE);
        Assertions.assertAll(
                () -> assertEquals(EMPLOYEE.getEmployeeId(), model.getEmployeeId()), //.getId() här??
                () -> assertEquals(EMPLOYEE.getFirstName(), model.getFirstName()),
                () -> assertEquals(EMPLOYEE.getLastName(), model.getLastName()),
                () -> assertEquals(EMPLOYEE.getSalary(), model.getSalary()),
                () -> assertEquals(EMPLOYEE.getDepartmentId(), model.getDepartmentId()),
                () -> assertEquals(EMPLOYEE.getFullTime(), model.getFullTime())
        );
    }
}