package se.jocke.employee.service;

import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.dao.EntityAlreadyInStorageException;
import se.jocke.dao.EntityNotFoundException;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Oscar Bergström 05-03-2021
 */
@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {

    // Test EmployeeService.java/EmployeeServiceImpl.java

    // Mock EmployeeDao since EmployeeServiceImpl is dependent on it
    @Mock
    private EmployeeDao employeeDao;

    // Will inject our mock into EmployeeServiceImpl since it's dependent on it
    @InjectMocks
    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Test
    public void getEmployeeById() {
        // Setup mock for this test
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(
                EmployeeDatabaseEntry.builder()
                        .employeeId(1)
                        .firstName("Brian")
                        .lastName("Kernighan")
                        .salary(BigDecimal.valueOf(50000))
                        .fullTime(true)
                        .departmentId(1)
                        .build()
        ));

        // Get Employee #1, this should grab data from our Mock
        Employee employee = employeeService.getEmployeeById(1);

        // Assert that we got the correct data
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("Brian", employee.getFirstName()),
                () -> Assertions.assertEquals("Kernighan", employee.getLastName()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(50000), employee.getSalary()),
                () -> Assertions.assertEquals(true, employee.getFullTime()),
                () -> Assertions.assertEquals(1, employee.getDepartmentId())
        );

        // Verify that "findById(1)" was called on our Mock once
        verify(employeeDao, times(1)).findById(1);

    }

    @Test
    public void getAllEmployees() {
        // Setup mock for this test
        when(employeeDao.findAll()).thenReturn(Arrays.asList(
                EmployeeDatabaseEntry.builder()
                        .employeeId(1)
                        .firstName("Brian")
                        .lastName("Kernighan")
                        .salary(BigDecimal.valueOf(50000))
                        .fullTime(true)
                        .departmentId(1)
                        .build(),
                EmployeeDatabaseEntry.builder()
                        .employeeId(2)
                        .firstName("Ken")
                        .lastName("Thompson")
                        .salary(BigDecimal.valueOf(65000))
                        .fullTime(false)
                        .departmentId(3)
                        .build()
        ));

        // Get all Employees, this should grab our Mock
        List<Employee> allEmployees = employeeService.getAllEmployees();

        // Assert that we got the correct data
        Assertions.assertAll(
                () -> Assertions.assertNotNull(allEmployees),
                () -> Assertions.assertEquals(2, allEmployees.size())
        );

        // Verify that "findAll" was called on our Mock once
        verify(employeeDao, times(1)).findAll();

    }

    @Test
    public void createEmployeeHappyFlow() {

        // Create a dummy Employee to test against
        Employee employee = EmployeeTestBuilder.builder().build();

        // Setup and mock method calls for this test
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(EmployeeDatabaseEntry.builder()
                .employeeId(employee.getEmployeeId().getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .salary(employee.getSalary())
                .fullTime(employee.getFullTime())
                .departmentId(employee.getDepartmentId())
                .build()
        );

        // Attempt to add our dummy Employee using the service under test
        Employee createdEmployee = employeeService.createEmployee(employee);

        // Assert that employeeService created an Employee with the correct data
        Assertions.assertAll(
                () -> Assertions.assertNotNull(createdEmployee),
                () -> Assertions.assertEquals(employee.getEmployeeId().getId(), createdEmployee.getEmployeeId().getId()),
                () -> Assertions.assertEquals(employee.getFirstName(), createdEmployee.getFirstName()),
                () -> Assertions.assertEquals(employee.getLastName(), createdEmployee.getLastName()),
                () -> Assertions.assertEquals(employee.getSalary(), createdEmployee.getSalary()),
                () -> Assertions.assertEquals(employee.getFullTime(), createdEmployee.getFullTime()),
                () -> Assertions.assertEquals(employee.getDepartmentId(), createdEmployee.getDepartmentId())
        );

        // Verify that .findById() and .save() was called on our mock once
        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));

    }

    @Test
    public void createEmployeeError() {
        // Create a dummy Employee to test against
        Employee employee = EmployeeTestBuilder.builder().build();

        // Setup and mock method call for this test
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(
                EmployeeDatabaseEntry.builder()
                        .employeeId(employee.getEmployeeId().getId())
                        .firstName(employee.getFirstName())
                        .lastName(employee.getLastName())
                        .salary(employee.getSalary())
                        .fullTime(employee.getFullTime())
                        .departmentId(employee.getDepartmentId())
                        .build()
        ));

        // Assert that an exception will be thrown, because the Employee should already be in the service database
        Throwable exceptionAlreadyInStorage = Assertions.assertThrows(EntityAlreadyInStorageException.class, () -> {
            employeeService.createEmployee(employee);
        });
        Assertions.assertEquals("Entity with id " + employee.getEmployeeId().getId() + " already in storage", exceptionAlreadyInStorage.getMessage());

        // Verify that .findById() was called on our mock once, and .save() wasn't
        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, never()).save(any());

    }

    @Test
    public void removeEmployeeHappyFlow() {
        // Create a dummy Employee to test against
        Employee employee = EmployeeTestBuilder.builder().build();

        // Setup and mock method call for this test
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(
                EmployeeDatabaseEntry.builder()
                        .employeeId(employee.getEmployeeId().getId())
                        .firstName(employee.getFirstName())
                        .lastName(employee.getLastName())
                        .salary(employee.getSalary())
                        .fullTime(employee.getFullTime())
                        .departmentId(employee.getDepartmentId())
                        .build()
        ));

        // The remove call will succeed since we Mock findById
        employeeService.removeEmployee(employee);

        // Verify that .findById() was called on our mock once
        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, times(1)).delete(any(EmployeeDatabaseEntry.class));

    }

    @Test
    public void removeEmployeeError() {
        // Create a dummy Employee to test against
        Employee employee = EmployeeTestBuilder.builder().build();

        // Assert that an exception will be thrown when we try to delete our dummy Employee,
        // because the Employee ID could not be found in the service
        Throwable exceptionEntityNotFound = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            employeeService.removeEmployee(employee);
        });
        Assertions.assertEquals("Entity with id " + employee.getEmployeeId().getId() + " not found", exceptionEntityNotFound.getMessage());

        // Verify that .findById() was called on our mock once, and .delete() wasn't
        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, never()).delete(any());
    }

    @Test
    public void updateEmployee() {
        // Create a dummy Employee to test against
        Employee employee = EmployeeTestBuilder.builder().build();

        // Setup and mock method calls for this test
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(
                EmployeeDatabaseEntry.builder()
                        .employeeId(employee.getEmployeeId().getId())
                        .firstName(employee.getFirstName())
                        .lastName(employee.getLastName())
                        .salary(employee.getSalary())
                        .fullTime(employee.getFullTime())
                        .departmentId(employee.getDepartmentId())
                        .build()
        ));
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(EmployeeDatabaseEntry.builder()
                .employeeId(employee.getEmployeeId().getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .salary(employee.getSalary())
                .fullTime(employee.getFullTime())
                .departmentId(employee.getDepartmentId())
                .build()
        );

        // Attempt to update Employee
        Employee updatedEmployee = employeeService.updateEmployee(employee);

        // Assert that employeeService created an Employee with the correct data
        Assertions.assertAll(
                () -> Assertions.assertNotNull(updatedEmployee),
                () -> Assertions.assertEquals(employee.getEmployeeId().getId(), updatedEmployee.getEmployeeId().getId()),
                () -> Assertions.assertEquals(employee.getFirstName(), updatedEmployee.getFirstName()),
                () -> Assertions.assertEquals(employee.getLastName(), updatedEmployee.getLastName()),
                () -> Assertions.assertEquals(employee.getSalary(), updatedEmployee.getSalary()),
                () -> Assertions.assertEquals(employee.getFullTime(), updatedEmployee.getFullTime()),
                () -> Assertions.assertEquals(employee.getDepartmentId(), updatedEmployee.getDepartmentId())
        );

        // Verify that .findById() and .save() was called on our mock once
        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));

    }

}
