package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.dao.EntityNotFoundException;
import se.jocke.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {
    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();

    @Test
    public void getAllEmployees() {
        Employee EMPLOYEE = EmployeeTestBuilder.builder().build();
        when(employeeDao.findAll()).thenReturn(
                Arrays.asList(EmployeeDatabaseEntry.builder()
                        .employeeId(EMPLOYEE.getEmployeeId().getId())
                        .departmentId(EMPLOYEE.getDepartmentId())
                        .firstName(EMPLOYEE.getFirstName())
                        .lastName(EMPLOYEE.getLastName())
                        .fullTime(EMPLOYEE.getFullTime())
                        .salary(EMPLOYEE.getSalary())
                        .build())
        );
        List<Employee> employees = systemUnderTest.getAllEmployees();
        Assertions.assertAll(
                () -> assertNotNull(employees),
                () -> assertEquals(1, employees.size()),
                () -> assertEquals(EMPLOYEE.getEmployeeId(), employees.get(0).getEmployeeId()),
                () -> assertEquals(EMPLOYEE.getDepartmentId(), employees.get(0).getDepartmentId()),
                () -> assertEquals(EMPLOYEE.getSalary(), employees.get(0).getSalary()),
                () -> assertEquals(EMPLOYEE.getFirstName(), employees.get(0).getFirstName()),
                () -> assertEquals(EMPLOYEE.getLastName(), employees.get(0).getLastName())
        );
    }
    @Test
    public void getEmployeeById() {
        Employee EMPLOYEE = EmployeeTestBuilder.builder().build();
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(
                EmployeeDatabaseEntry.builder()
                        .employeeId(EMPLOYEE.getEmployeeId().getId())
                        .departmentId(EMPLOYEE.getDepartmentId())
                        .firstName(EMPLOYEE.getFirstName())
                        .lastName(EMPLOYEE.getLastName())
                        .fullTime(EMPLOYEE.getFullTime())
                        .salary(EMPLOYEE.getSalary())
                        .build()
        ));

        Employee employee = systemUnderTest.getEmployeeById(EMPLOYEE.getEmployeeId().getId());

        Assertions.assertAll(
                () -> assertNotNull(employee),
                () -> assertEquals(EMPLOYEE.getEmployeeId(), employee.getEmployeeId()),
                () -> assertEquals(EMPLOYEE.getDepartmentId(), employee.getDepartmentId()),
                () -> assertEquals(EMPLOYEE.getSalary(), employee.getSalary()),
                () -> assertEquals(EMPLOYEE.getFirstName(), employee.getFirstName()),
                () -> assertEquals(EMPLOYEE.getLastName(), employee.getLastName())
        );
    }
    @Test
    public void getEmployeeByIdErrorMessage() {
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        Throwable throwable = Assertions.assertThrows(EntityNotFoundException.class, () ->
                systemUnderTest.getEmployeeById(1)
        );
        Assertions.assertAll(
                () -> assertNotNull(throwable),
                () -> assertEquals("Entity with id 1 not found",throwable.getMessage())
        );
    }
    @Test
    public void updateEmployee() {
        Employee EMPLOYEE = EmployeeTestBuilder.builder().build();
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(
                EmployeeDatabaseEntry.builder()
                        .employeeId(EMPLOYEE.getEmployeeId().getId())
                        .departmentId(EMPLOYEE.getDepartmentId())
                        .firstName(EMPLOYEE.getFirstName())
                        .lastName(EMPLOYEE.getLastName())
                        .fullTime(EMPLOYEE.getFullTime())
                        .salary(EMPLOYEE.getSalary())
                        .build()
        ));
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(EmployeeDatabaseEntry.builder()
                .employeeId(EMPLOYEE.getEmployeeId().getId())
                .departmentId(EMPLOYEE.getDepartmentId())
                .firstName(EMPLOYEE.getFirstName())
                .lastName(EMPLOYEE.getLastName())
                .fullTime(EMPLOYEE.getFullTime())
                .salary(EMPLOYEE.getSalary())
                .build());

        Employee updatedEmployee = systemUnderTest.updateEmployee(EMPLOYEE);
        Assertions.assertAll(
                () -> assertNotNull(updatedEmployee),
                () -> assertEquals(EMPLOYEE.getEmployeeId(), updatedEmployee.getEmployeeId()),
                () -> assertEquals(EMPLOYEE.getDepartmentId(), updatedEmployee.getDepartmentId()),
                () -> assertEquals(EMPLOYEE.getSalary(), updatedEmployee.getSalary()),
                () -> assertEquals(EMPLOYEE.getFirstName(), updatedEmployee.getFirstName()),
                () -> assertEquals(EMPLOYEE.getLastName(), updatedEmployee.getLastName())
        );
    }
    @Test
    public void updateEmployeeErrorMessage() {
        Employee EMPLOYEE = EmployeeTestBuilder.builder().build();
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        Throwable throwable = Assertions.assertThrows(EntityNotFoundException.class, () ->
                systemUnderTest.updateEmployee(EMPLOYEE)
        );
        Assertions.assertAll(
                () -> assertNotNull(throwable),
                () -> assertEquals("Entity with id "+EMPLOYEE.getEmployeeId().getId()+" not found",
                        throwable.getMessage())
        );
    }
}
