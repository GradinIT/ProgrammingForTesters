package se.jocke.employee.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.jocke.api.EmployeeModel;
import se.jocke.api.mapper.EmployeeModelMapper;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeModelTestBuilder;
import se.jocke.employee.builder.EmployeeTestBuilder;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


public class TestEmployeeModelMapper {
    private final EmployeeModel EMPLOYEE_MODEL = EmployeeModelTestBuilder.builder().build();
    private final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();

    // Testing that the builders from employee and employee model builds objects correctly with correct data.
    @DisplayName("Testing all variables with employee test builder")
    @Test
    public void testEmployeeToEmployeeModelMapping() {
        EmployeeModel model = EmployeeModelMapper.map(EMPLOYEE);
        Assertions.assertAll(
                () -> assertEquals(EMPLOYEE.getEmployeeId().getId(), model.getEmployeeId()),
                () -> assertEquals(EMPLOYEE.getFirstName(), model.getFirstName()),
                () -> assertEquals(EMPLOYEE.getLastName(), model.getLastName()),
                () -> assertEquals(EMPLOYEE.getSalary(), model.getSalary()),
                () -> assertEquals(EMPLOYEE.getFullTime(), model.getFullTime())
        );
    }

    @DisplayName("Testing all variables with employee model test builder")
    @Test
    public void testEmployeeModelToEmployeeMapping() {
        Employee employee = EmployeeModelMapper.map(EMPLOYEE_MODEL);
        Assertions.assertAll(
                () -> assertEquals(EMPLOYEE_MODEL.getEmployeeId(), employee.getEmployeeId().getId()),
                () -> assertEquals(EMPLOYEE_MODEL.getFirstName(), employee.getFirstName()),
                () -> assertEquals(EMPLOYEE_MODEL.getLastName(), employee.getLastName()),
                () -> assertEquals(EMPLOYEE_MODEL.getSalary(), employee.getSalary()),
                () -> assertEquals(EMPLOYEE_MODEL.getFullTime(), employee.getFullTime())
        );
    }

    @DisplayName("Testing builders with hardcoded data")
    @Test
    public void testEmployeeBuildersHardCoded() {
        assertAll(
                ()-> assertNotNull(EMPLOYEE),
                () -> assertEquals(EMPLOYEE.getEmployeeId().getId(), 100),
                () -> assertEquals(EMPLOYEE.getFirstName(), "Testare"),
                () -> assertEquals(EMPLOYEE.getLastName(), "Testarsson"),
                () -> assertEquals(EMPLOYEE.getFullTime(), true),
                () -> assertEquals(EMPLOYEE.getSalary(), BigDecimal.valueOf(25000.0)),
                () -> assertEquals(EMPLOYEE.getDepartmentId(), 1)
        );

        assertAll(
                () -> assertNotNull(EMPLOYEE_MODEL),
                () -> assertEquals(EMPLOYEE_MODEL.getEmployeeId(), 200),
                () -> assertEquals(EMPLOYEE_MODEL.getFirstName(), "Anders"),
                () -> assertEquals(EMPLOYEE_MODEL.getLastName(), "Andersson"),
                () -> assertEquals(EMPLOYEE_MODEL.getSalary(), BigDecimal.valueOf(25000)),
                () -> assertEquals(EMPLOYEE_MODEL.getFullTime(), true),
                () -> assertEquals(EMPLOYEE_MODEL.getDepartmentId(), 1)
        );
    }
}