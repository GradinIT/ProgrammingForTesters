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
/*
    @BeforeEach
    public void setUp() {
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("firstName1")
                .lastName("lastName1")
                .fullTime(true)
                .salary(BigDecimal.valueOf(25000))
                .departmentId(1)
                .build()));
    }
*/
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
                () -> Assertions.assertEquals(1, employee.getDepartmentId()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(25000), employee.getSalary()),
                () -> Assertions.assertEquals(true, employee.getFullTime())
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

            () -> assertNotNull(createEmployee),
            () -> assertEquals(employee.getEmployeeId().getId(), createEmployee.getEmployeeId().getId()),
            () -> assertEquals(employee.getFirstName(), createEmployee.getFirstName()),
            () -> assertEquals(employee.getLastName(), createEmployee.getLastName()),
            () -> assertEquals(employee.getSalary(), createEmployee.getSalary()),
            () -> assertEquals(employee.getFullTime(), createEmployee.getFullTime()),
            () -> assertEquals(employee.getDepartmentId(), createEmployee.getDepartmentId())

    );
    verify(employeeDao, times(1)).findById(any(Integer.class));
    verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void getAllEmployees(){
        when(employeeDao.findAll()).thenReturn(Arrays.asList(EmployeeDatabaseEntry.builder()
        .employeeId(1)
        .firstName("")
        .lastName("")
        .salary(BigDecimal.ONE)
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
    public void createEmployeeError(){
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
