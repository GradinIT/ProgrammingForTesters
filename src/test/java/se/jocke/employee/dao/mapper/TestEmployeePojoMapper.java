package se.jocke.employee.dao.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.dao.mapper.EmployeePojoMapper;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test EmployeePojoMapper")
public class TestEmployeePojoMapper {

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = EmployeeTestBuilder.builder().build();
    }

    @Test
    @DisplayName("When we map employeeDatabaseEntry to employee")
    public void testMapEmployeeDatabaseEntryToEmployee() {

        EmployeeDatabaseEntry employeeDatabaseEntry = EmployeeDatabaseEntry.builder()
                .employeeId(employee.getEmployeeId().getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .fullTime(employee.getFullTime())
                .salary(employee.getSalary())
                .departmentId(employee.getDepartmentId())
                .build();

        Employee result = EmployeePojoMapper.map(employeeDatabaseEntry);

        Assertions.assertAll(
                () -> assertEquals(employeeDatabaseEntry.getEmployeeId(), result.getEmployeeId().getId()),
                () -> assertEquals(employeeDatabaseEntry.getFirstName(), result.getFirstName()),
                () -> assertEquals(employeeDatabaseEntry.getLastName(), result.getLastName()),
                () -> assertEquals(employeeDatabaseEntry.getFullTime(), result.getFullTime()),
                () -> assertEquals(employeeDatabaseEntry.getSalary(), result.getSalary()),
                () -> assertEquals(employeeDatabaseEntry.getDepartmentId(), result.getDepartmentId())
        );
    }

    @Test
    @DisplayName("When we map employee to employeeDatabaseEntry")
    public void testMapEmployeeToEmployeeDatabaseEntry() {

        EmployeeDatabaseEntry employeeDatabaseEntry = EmployeePojoMapper.map(employee);

        Assertions.assertAll(
                () -> assertEquals(employee.getEmployeeId().getId(), employeeDatabaseEntry.getEmployeeId()),
                () -> assertEquals(employee.getFirstName(), employeeDatabaseEntry.getFirstName()),
                () -> assertEquals(employee.getLastName(), employeeDatabaseEntry.getLastName()),
                () -> assertEquals(employee.getFullTime(), employeeDatabaseEntry.getFullTime()),
                () -> assertEquals(employee.getSalary(), employeeDatabaseEntry.getSalary()),
                () -> assertEquals(employee.getDepartmentId(), employeeDatabaseEntry.getDepartmentId())
        );
    }

    @Test
    @DisplayName("When we map list of employeeDatabaseEntry to list of employees")
    public void testMapEmployeeDatabaseEntryListToEmployeeList() {

        List<EmployeeDatabaseEntry> employeeDatabaseEntryList = Arrays.asList(EmployeeDatabaseEntry.builder()
                .employeeId(employee.getEmployeeId().getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .fullTime(employee.getFullTime())
                .salary(employee.getSalary())
                .departmentId(employee.getDepartmentId())
                .build());

        List<Employee> employeeList = EmployeePojoMapper.map(employeeDatabaseEntryList);

        Assertions.assertAll(
                () -> assertFalse(employeeList.isEmpty()),
                () -> assertEquals(1, employeeList.size())
        );
    }
}
