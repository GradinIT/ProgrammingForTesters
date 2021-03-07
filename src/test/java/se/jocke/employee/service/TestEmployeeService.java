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
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;
import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {
    // Mocking EmployeeDao to get database values (changelog)
    @Mock
    private EmployeeDao employeeDao;

    // Injecting values in new EmployeeService object for comparison
    @InjectMocks
    private EmployeeService employeeSystemUnderTest = new EmployeeServiceImpl();

    // Setting up values for tests, same values as in changelog file for ID 1
    @BeforeEach
    public void setUp() {
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("firstName1")
                .lastName("lastName1")
                .salary(BigDecimal.valueOf(25000))
                .fullTime(true)
                .departmentId(1)
                .build()));
    }

    // Testing that the injected mock values are the same as expected
    @DisplayName("Testning that mocked object return right values")
    @Test
    public void findById() {
        Employee employee = employeeSystemUnderTest.getEmployeeById(1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("firstName1", employee.getFirstName()),
                () -> Assertions.assertEquals("lastName1", employee.getLastName()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(25000), employee.getSalary()),
                () -> Assertions.assertEquals(true, employee.getFullTime()),
                () -> Assertions.assertEquals(1, employee.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(1);
    }

    // Happyflow test
    @DisplayName("When life is perfect and everything works the way you want it to")
    @Test
    public void createEmployeeHappyFlow() {
        Employee employee = EmployeeTestBuilder.builder().build();
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(EmployeeDatabaseEntry.builder()
                .employeeId(employee.getEmployeeId().getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .salary((employee.getSalary()))
                .fullTime(employee.getFullTime())
                .departmentId(employee.getDepartmentId())
                .build());


        Employee createdEmployee = employeeSystemUnderTest.createEmployee(employee);
        Assertions.assertAll(
                () -> assertNotNull(createdEmployee),
                () -> assertEquals(employee.getEmployeeId().getId(), createdEmployee.getEmployeeId().getId()),
                () -> assertEquals(employee.getFirstName(), createdEmployee.getFirstName()),
                () -> assertEquals(employee.getLastName(), createdEmployee.getLastName()),
                () -> assertEquals(employee.getSalary(), createdEmployee.getSalary()),
                () -> assertEquals(employee.getFullTime(), createdEmployee.getFullTime()),
                () -> assertEquals(employee.getDepartmentId(), createdEmployee.getDepartmentId())
        );

        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    // Error test
    @DisplayName("When life is not as good and the employee already exists")
    @Test
    public void createEmployeeError() {
        Employee employee = EmployeeTestBuilder.builder().build();
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(employee.getEmployeeId().getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .salary((employee.getSalary()))
                .fullTime(employee.getFullTime())
                .departmentId(employee.getDepartmentId())
                .build()));

        Throwable exception = Assertions.assertThrows(EntityAlreadyInStorageException.class, () -> {
            employeeSystemUnderTest.createEmployee(employee);
        });
        Assertions.assertEquals("Entity with id "+employee.getEmployeeId().getId()+" already in storage",
                exception.getMessage());
        verify(employeeDao, times(1)).findById(any(Integer.class));
        verifyNoMoreInteractions(employeeDao);
    }
}
