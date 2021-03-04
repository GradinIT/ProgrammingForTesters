package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.dao.EntityAlreadyInStorageException;
import se.jocke.dao.EntityNotFoundException;
import se.jocke.dao.mapper.EmployeePojoMapper;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {

    private Employee employee;
    private Employee employeeResult;
    private EmployeeDatabaseEntry employeeDatabaseEntry;

    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private final EmployeeService SYSTEM_UNDER_TEST = new EmployeeServiceImpl();

    @BeforeEach
    void setUp() {
        employeeResult = null;
        employee = EmployeeTestBuilder.builder().build();
        employeeDatabaseEntry = EmployeeDatabaseEntry.builder()
                .employeeId(employee.getEmployeeId().getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .salary(employee.getSalary())
                .fullTime(employee.getFullTime())
                .departmentId(employee.getDepartmentId())
                .build();
    }

    @Test
    @DisplayName("When client get employee by ID")
    public void testFindEmployee() {
        when(employeeDao.findById(anyInt())).thenReturn(Optional.of(employeeDatabaseEntry));

        employeeResult = SYSTEM_UNDER_TEST.getEmployeeById(employee.getEmployeeId().getId());
        Assertions.assertAll(
                () -> assertEquals(employee.getEmployeeId().getId(), employeeResult.getEmployeeId().getId()),
                () -> assertEquals(employee.getFirstName(), employeeResult.getFirstName()),
                () -> assertEquals(employee.getLastName(), employeeResult.getLastName()),
                () -> assertEquals(employee.getSalary(), employeeResult.getSalary()),
                () -> assertEquals(employee.getFullTime(), employeeResult.getFullTime()),
                () -> assertEquals(employee.getDepartmentId(), employeeResult.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(employee.getEmployeeId().getId());
    }

    @Test
    @DisplayName("When employee isn't represent")
    public void testFindEmployeeThrowsEntityNotFindException() {
        when(employeeDao.findById(anyInt())).thenReturn(Optional.empty());

        Throwable exception = Assertions.assertThrows(EntityNotFoundException.class, () -> SYSTEM_UNDER_TEST.getEmployeeById(1));

        assertEquals("Entity with id " + 1  + " not found", exception.getMessage());

        verify(employeeDao, times(1)).findById(anyInt());
    }

    @Test
    @DisplayName("When client create employee")
    public void testCreateEmployeeHappyFlow() {

        when(employeeDao.findById(employee.getEmployeeId().getId())).thenReturn(Optional.empty());
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(employeeDatabaseEntry);

        employeeResult = SYSTEM_UNDER_TEST.createEmployee(employee);

        Assertions.assertAll(
                () -> assertEquals(employee.getEmployeeId().getId(), employeeResult.getEmployeeId().getId()),
                () -> assertEquals(employee.getFirstName(), employeeResult.getFirstName()),
                () -> assertEquals(employee.getLastName(), employeeResult.getLastName()),
                () -> assertEquals(employee.getSalary(), employeeResult.getSalary()),
                () -> assertEquals(employee.getFullTime(), employeeResult.getFullTime()),
                () -> assertEquals(employee.getDepartmentId(), employeeResult.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(anyInt());
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    @DisplayName("When client try to create employee that already exists")
    public void testCreateEmployeeEntityAlreadyInStorageException() {
        when(employeeDao.findById(anyInt())).thenReturn(Optional.of(employeeDatabaseEntry));

        Throwable exception = Assertions.assertThrows(EntityAlreadyInStorageException.class, () -> SYSTEM_UNDER_TEST.createEmployee(employee));

        Assertions.assertEquals("Entity with id " + employee.getEmployeeId().getId() + " already in storage", exception.getMessage());

        verify(employeeDao, times(1)).findById(anyInt());
        verifyNoMoreInteractions(employeeDao);
    }

    @Test
    @DisplayName("When client delete employee")
    public void testRemoveEmployeeHappyFlow() {
        when(employeeDao.findById(anyInt())).thenReturn(Optional.of(employeeDatabaseEntry));

        employeeResult = SYSTEM_UNDER_TEST.removeEmployee(employee);

        Assertions.assertAll(
                () -> assertEquals(employee.getEmployeeId().getId(), employeeResult.getEmployeeId().getId()),
                () -> assertEquals(employee.getFirstName(), employeeResult.getFirstName()),
                () -> assertEquals(employee.getLastName(), employeeResult.getLastName()),
                () -> assertEquals(employee.getSalary(), employeeResult.getSalary()),
                () -> assertEquals(employee.getFullTime(), employeeResult.getFullTime()),
                () -> assertEquals(employee.getDepartmentId(), employeeResult.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(anyInt());
        verify(employeeDao, times(1)).delete(any(EmployeeDatabaseEntry.class));
    }

    @Test
    @DisplayName("When client try to delete employee that not exists")
    public void testRemoveEmployeeEntityNotFoundException() {
        when(employeeDao.findById(anyInt())).thenReturn(Optional.empty());

        Throwable exception = Assertions.assertThrows(EntityNotFoundException.class, () -> SYSTEM_UNDER_TEST.removeEmployee(employee));

        assertEquals("Entity with id " + employee.getEmployeeId().getId() + " not found", exception.getMessage());

        verify(employeeDao, times(1)).findById(anyInt());
        verifyNoMoreInteractions(employeeDao);
    }

    @Test
    @DisplayName("When client update employee")
    public void testUpdateEmployeeHappyFlow() {

        BigDecimal newSalary = new BigDecimal("36000.00");

        Employee employeeToUpdate = Employee.builder()
                .employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .fullTime(employee.getFullTime())
                .salary(newSalary)
                .departmentId(employee.getDepartmentId())
                .build();

        when(employeeDao.findById(anyInt())).thenReturn(Optional.of(EmployeePojoMapper.map(employeeToUpdate)));
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(EmployeePojoMapper.map(employeeToUpdate));

        employeeResult = SYSTEM_UNDER_TEST.updateEmployee(employee);

        Assertions.assertAll(
                () -> assertEquals(employeeToUpdate.getEmployeeId().getId(), employeeResult.getEmployeeId().getId()),
                () -> assertEquals(employeeToUpdate.getFirstName(), employeeResult.getFirstName()),
                () -> assertEquals(employeeToUpdate.getLastName(), employeeResult.getLastName()),
                () -> assertEquals(employeeToUpdate.getSalary(), employeeResult.getSalary()),
                () -> assertEquals(employeeToUpdate.getFullTime(), employeeResult.getFullTime()),
                () -> assertEquals(employeeToUpdate.getDepartmentId(), employeeResult.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(anyInt());
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    @DisplayName("When client update employee that not exists")
    public void testUpdateEmployeeEntityNotFoundException() {
        when(employeeDao.findById(anyInt())).thenReturn(Optional.empty());

        Throwable exception = Assertions.assertThrows(EntityNotFoundException.class, () -> SYSTEM_UNDER_TEST.updateEmployee(employee));

        assertEquals("Entity with id " + employee.getEmployeeId().getId() + " not found", exception.getMessage());

        verify(employeeDao, times(1)).findById(anyInt());
        verifyNoMoreInteractions(employeeDao);
    }

    @Test
    @DisplayName("When client request list with all employees")
    public void testGetAllEmployees() {
        List<EmployeeDatabaseEntry> employees = new ArrayList<>();
        employees.add(employeeDatabaseEntry);
        employees.add(employeeDatabaseEntry);
        employees.add(employeeDatabaseEntry);

        when(employeeDao.findAll()).thenReturn(employees);

        List<Employee> tempEmpList = SYSTEM_UNDER_TEST.getAllEmployees();

        Assertions.assertEquals(3, tempEmpList.size());

        verify(employeeDao, times(1)).findAll();
    }
}
