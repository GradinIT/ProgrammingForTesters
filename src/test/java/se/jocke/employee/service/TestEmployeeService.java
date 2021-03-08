package se.jocke.employee.service;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {
    @Mock
    private EmployeeDao employeeDao;
    @InjectMocks
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();

    @Test
    public void findById() {
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("firstName1")
                .lastName("lastName1")
                .fullTime(true)
                .salary(BigDecimal.valueOf(25000))
                .departmentId(1)
                .build()));

        Employee employee = systemUnderTest.getEmployeeById(1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("firstName1", employee.getFirstName()),
                () -> Assertions.assertEquals("lastName1", employee.getLastName()),
                () -> Assertions.assertEquals(true, employee.getFullTime()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(25000), employee.getSalary()),
                () -> Assertions.assertEquals(1, employee.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(1);
    }

    @Test
    public void createEmployeeHappyFlow(){
        Employee employee = EmployeeTestBuilder.builder().build();
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(EmployeeDatabaseEntry.builder()
                .employeeId(employee.getEmployeeId().getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .salary(employee.getSalary())
                .fullTime(employee.getFullTime())
                .departmentId(employee.getDepartmentId())
                .build());
        Employee createEmployee = systemUnderTest.createEmployee(employee);
        Assertions.assertAll(
                () -> Assertions.assertEquals(employee.getEmployeeId().getId(), createEmployee.getEmployeeId().getId()),
                () -> Assertions.assertEquals(employee.getFirstName(), createEmployee.getFirstName()),
                () -> Assertions.assertEquals(employee.getLastName(), createEmployee.getLastName()),
                () -> Assertions.assertEquals(employee.getFullTime(), createEmployee.getFullTime()),
                () -> Assertions.assertEquals(employee.getSalary(), createEmployee.getSalary()),
                () -> Assertions.assertEquals(employee.getDepartmentId(), createEmployee.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void updateEmployeeHappyFlow(){
        Employee employee = EmployeeTestBuilder.builder().build();
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(employee.getEmployeeId().getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .salary(employee.getSalary())
                .fullTime(employee.getFullTime())
                .departmentId(employee.getDepartmentId())
                .build()));
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(EmployeeDatabaseEntry.builder()
                .employeeId(employee.getEmployeeId().getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .salary(employee.getSalary())
                .fullTime(employee.getFullTime())
                .departmentId(employee.getDepartmentId())
                .build());
        Employee updateEmployee = systemUnderTest.updateEmployee(employee);
        Assertions.assertAll(
                () -> assertEquals(employee.getEmployeeId().getId(), updateEmployee.getEmployeeId().getId()),
                () -> assertEquals(employee.getFirstName(), updateEmployee.getFirstName()),
                () -> assertEquals(employee.getLastName(), updateEmployee.getLastName()),
                () -> assertEquals(employee.getFullTime(), updateEmployee.getFullTime()),
                () -> assertEquals(employee.getSalary(), updateEmployee.getSalary()),
                () -> assertEquals(employee.getDepartmentId(), updateEmployee.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void remove(){
        Employee employee = EmployeeTestBuilder.builder().build();
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(employee.getEmployeeId().getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .salary(employee.getSalary())
                .fullTime(employee.getFullTime())
                .departmentId(employee.getDepartmentId())
                .build()));
        Employee removeEmployee = systemUnderTest.removeEmployee(employee);
        Assertions.assertAll(
                () -> Assertions.assertEquals(employee.getEmployeeId().getId(), removeEmployee.getEmployeeId().getId()),
                () -> Assertions.assertEquals(employee.getFirstName(), removeEmployee.getFirstName()),
                () -> Assertions.assertEquals(employee.getLastName(), removeEmployee.getLastName()),
                () -> Assertions.assertEquals(employee.getSalary(), removeEmployee.getSalary()),
                () -> Assertions.assertEquals(employee.getFullTime(), removeEmployee.getFullTime()),
                () -> Assertions.assertEquals(employee.getDepartmentId(), removeEmployee.getDepartmentId())
        );
    }

    @Test
    public void getAll(){
        when(employeeDao.findAll()).thenReturn(Arrays.asList(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("Johanna")
                .lastName("Salmi")
                .salary(BigDecimal.valueOf(20000))
                .fullTime(Boolean.TRUE)
                .departmentId(1)
                .build()));

        List<Employee> employees = systemUnderTest.getAllEmployees();
        Assertions.assertAll(
                () -> assertNotNull(employees),
                () -> assertEquals(1, employees.size())
        );
    }
}
