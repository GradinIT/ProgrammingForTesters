package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.api.EmployeeModel;
import se.jocke.dao.DepartmentDatabaseEntry;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.dao.EntityAlreadyInStorageException;
import se.jocke.dao.mapper.EmployeePojoMapper;
import se.jocke.department.builder.DepartmentTestBuilder;
import se.jocke.department.entity.Department;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
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
    private Optional<EmployeeDatabaseEntry> databaseEntryOptional;

    @BeforeEach
    public void setUp() {
        databaseEntryOptional = Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("Hanna")
                .lastName("test")
                .fullTime(true)
                .salary(BigDecimal.valueOf(4000))
                .departmentId(1)
                .build());
    }

    @Test
    public void findById() {

        databaseEntryOptional.ifPresent(employeeDatabaseEntry -> when(employeeDao.findById(any(Integer.class)))
                .thenReturn(Optional.of(employeeDatabaseEntry)));

        Employee employee = systemUnderTest.getEmployeeById(1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("Hanna", employee.getFirstName())
        );
        verify(employeeDao, times(1)).findById(1);
    }

    @Test
    public void getAllEmployees() {

        databaseEntryOptional.ifPresent(employeeDatabaseEntry -> when(employeeDao.findAll())
                .thenReturn(Collections.singletonList(employeeDatabaseEntry)));

        List<Employee> employees = systemUnderTest.getAllEmployees();
        Assertions.assertAll(
                () -> assertNotNull(employees),
                () -> assertEquals(1, employees.size())
        );
    }

    @Test
    public void createEmployeeHappyFlow() {
        Employee employee = EmployeeTestBuilder.builder().build();
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(EmployeeDatabaseEntry.builder()
                .employeeId(employee.getEmployeeId().getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .fullTime(employee.getFullTime())
                .salary(employee.getSalary())
                .departmentId(employee.getDepartmentId())
                .build());

        Employee createdEmployee = systemUnderTest.createEmployee(employee);
        Assertions.assertAll(
                () -> assertNotNull(createdEmployee),
                () -> assertEquals(employee.getEmployeeId().getId(), createdEmployee.getEmployeeId().getId()),
                () -> assertEquals(employee.getFirstName(), createdEmployee.getFirstName())
        );
        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void createEmployeeError() {
        Employee employee = EmployeeTestBuilder.builder().build();
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeePojoMapper.map(
                employee)));

        Throwable exception = Assertions.assertThrows(EntityAlreadyInStorageException.class, () -> {
            systemUnderTest.createEmployee(employee);
        });
        Assertions.assertEquals("Entity with id "+employee.getEmployeeId().getId()+" already in storage",
                exception.getMessage());
        verify(employeeDao, times(1)).findById(any(Integer.class));
        verifyNoMoreInteractions(employeeDao);
    }
}