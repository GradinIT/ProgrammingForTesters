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
import se.jocke.dao.EntityAlreadyInStorageException;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;
import java.math.BigDecimal;
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
    private final EmployeeService systemUnderTest = new EmployeeServiceImpl();

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void findById() {
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(2)
                .firstName("MockTest")
                .lastName("MockTest2")
                .fullTime(true)
                .salary(BigDecimal.valueOf(45000.00))
                .departmentId(15)
                .build()));
        Employee employee = systemUnderTest.getEmployeeById(2);
        Assertions.assertAll(
                () -> Assertions.assertEquals(2, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("MockTest", employee.getFirstName()),
                () -> Assertions.assertEquals("MockTest2", employee.getLastName()),
                () -> Assertions.assertEquals(true, employee.getFullTime()),
                () -> Assertions.assertEquals((BigDecimal.valueOf(45000.00)), employee.getSalary()),
                () -> Assertions.assertEquals(15, employee.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(2);
    }


    @Test
    public void getAllEmployees() {
        when(employeeDao.findAll()).thenReturn(Collections.singletonList(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("firstName1")
                .lastName("lastName2")
                .salary(BigDecimal.valueOf(25000))
                .fullTime(true)
                .departmentId(1)
                .build()));

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
                .salary(employee.getSalary())
                .fullTime(employee.getFullTime())
                .departmentId(employee.getDepartmentId())
                .build());
        Employee createdEmployees = systemUnderTest.createEmployee(employee);
        Assertions.assertAll(
                () -> assertNotNull(createdEmployees),
                () -> assertEquals(employee.getEmployeeId().getId(), createdEmployees.getEmployeeId().getId()),
                () -> assertEquals(employee.getFirstName(), createdEmployees.getFirstName()),
                () -> assertEquals(employee.getLastName(), createdEmployees.getLastName()),
                () -> assertEquals(employee.getSalary(), createdEmployees.getSalary()),
                () -> assertEquals(employee.getFullTime(), createdEmployees.getFullTime()),
                () -> assertEquals(employee.getDepartmentId(), createdEmployees.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void createEmployeeError() {
        Employee employee = EmployeeTestBuilder.builder().build();
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
        Throwable exception = Assertions.assertThrows(EntityAlreadyInStorageException.class, () -> {
            systemUnderTest.createEmployee(employee);
        });
        Assertions.assertEquals("Entity with id "+employee.getEmployeeId().getId()+" already in storage",
                exception.getMessage());
        verify(employeeDao, times(1)).findById(any(Integer.class));
        verifyNoMoreInteractions(employeeDao);
    }
}


