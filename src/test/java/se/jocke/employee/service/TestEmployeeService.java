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

import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {
    @Mock
    private EmployeeDao employeeDao;
    @InjectMocks
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();

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

    @Test
    public void findById() {
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
}
