package se.jocke.employee.old.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.common.dao.EntityAlreadyInStorageException;
import se.jocke.employee.old.dao.EmployeeDao;
import se.jocke.employee.old.dao.EmployeeDatabaseEntry;
import se.jocke.employee.old.entity.Employee;
import se.jocke.employee.test.builder.EmployeeDatabaseEntryTestBuilder;
import se.jocke.employee.test.builder.EmployeeTestBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {
    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void findById() {

        when(employeeDao.findById(any(Integer.class)))
                .thenReturn(Optional.of(EmployeeDatabaseEntryTestBuilder.builder().build()));

        Employee employee = systemUnderTest.getEmployeeById(1);
        Assertions.assertAll(
                () -> assertEquals(1, employee.getEmployeeId().getId()),
                () -> assertEquals("Jocke", employee.getFirstName())
        );
        verify(employeeDao, times(1)).findById(1);
    }

    @Test
    public void getAllEmployees() {
        when(employeeDao.findAll())
                .thenReturn(Arrays.asList(EmployeeDatabaseEntryTestBuilder.builder().build()));

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
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(EmployeeDatabaseEntryTestBuilder.builder().build());
        Employee createdEmployee = systemUnderTest.createEmployee(employee);
        Assertions.assertAll(
                () -> assertNotNull(createdEmployee),
                () -> assertEquals(employee.getEmployeeId(), createdEmployee.getEmployeeId()),
                () -> assertEquals(employee.getFirstName(), createdEmployee.getFirstName())
        );
        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void createEmployeeError() {
        Employee employee = EmployeeTestBuilder.builder().build();
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(
                EmployeeDatabaseEntryTestBuilder.builder().build()));
        Throwable exception = Assertions.assertThrows(EntityAlreadyInStorageException.class, () -> {
            systemUnderTest.createEmployee(employee);
        });
        Assertions.assertEquals("Entity with id " + employee.getEmployeeId().getId() + " already in storage",
                exception.getMessage());
        verify(employeeDao, times(1)).findById(any(Integer.class));
        verifyNoMoreInteractions(employeeDao);
    }
}


