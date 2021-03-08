package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.dao.mapper.EmployeePojoMapper;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.department.entity.Employee;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {

    EmployeeDatabaseEntry employeeDatabaseEntry;

    @Mock
    private EmployeeDao employeeDao;
    @InjectMocks
    private final EmployeeService systemUnderTest = new EmployeeServiceImpl();

    @BeforeEach
    public void setUp() {
        employeeDatabaseEntry = EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("Hanna")
                .lastName("Olsson")
                .salary(BigDecimal.valueOf(40000))
                .fullTime(true)
                .departmentId(1)
                .build();
    }

    @Test
    public void findById() {

        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(employeeDatabaseEntry));

        Employee employee = systemUnderTest.getEmployeeById(1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("Hanna", employee.getFirstName()),
                () -> Assertions.assertEquals("Olsson", employee.getLastName()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(40000), employee.getSalary()),
                () -> Assertions.assertEquals(1, employee.getDepartmentId()),
                () -> Assertions.assertEquals(true, employee.getFullTime())
        );

        verify(employeeDao, times(1)).findById(anyInt());
    }

    @Test
    public void create() {

        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(employeeDatabaseEntry);

        Employee employee = systemUnderTest.createEmployee(EmployeeTestBuilder.builder().build());

        Assertions.assertAll(
                () -> Assertions.assertNotNull(employee),
                () -> Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("Hanna", employee.getFirstName()),
                () -> Assertions.assertEquals("Olsson", employee.getLastName()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(40000), employee.getSalary()),
                () -> Assertions.assertEquals(1, employee.getDepartmentId()),
                () -> Assertions.assertEquals(true, employee.getFullTime())
        );

        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void update() {

        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(employeeDatabaseEntry));
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(employeeDatabaseEntry);

        Employee employee = systemUnderTest.updateEmployee(systemUnderTest.getEmployeeById(1));

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("Hanna", employee.getFirstName()),
                () -> Assertions.assertEquals("Olsson", employee.getLastName()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(40000), employee.getSalary()),
                () -> Assertions.assertEquals(1, employee.getDepartmentId()),
                () -> Assertions.assertEquals(true, employee.getFullTime())
        );

        verify(employeeDao, times(2)).findById(anyInt());
        verify(employeeDao, times(1)).save(EmployeePojoMapper.map(employee));
    }

    @Test
    public void remove() {

        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(employeeDatabaseEntry));

        Employee employee = systemUnderTest.removeEmployee(EmployeePojoMapper.map(Optional.of(employeeDatabaseEntry).get()));

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("Hanna", employee.getFirstName()),
                () -> Assertions.assertEquals("Olsson", employee.getLastName()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(40000), employee.getSalary()),
                () -> Assertions.assertEquals(1, employee.getDepartmentId()),
                () -> Assertions.assertEquals(true, employee.getFullTime())
        );

        verify(employeeDao, times(1)).findById(anyInt());
        verify(employeeDao, times(1)).delete(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void getAll() {

        when(employeeDao.findAll()).thenReturn(Collections.singletonList(employeeDatabaseEntry));

        List<Employee> employees = systemUnderTest.getAllEmployees();

        Assertions.assertAll(
                () -> Assertions.assertNotNull(employees),
                () -> Assertions.assertEquals(1, employees.size())
        );

        verify(employeeDao, times(1)).findAll();

    }

}