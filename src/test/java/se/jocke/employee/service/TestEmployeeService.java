package se.jocke.employee.service;

import io.cucumber.java.sl.In;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
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
import static org.mockito.ArgumentMatchers.booleanThat;
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
    public void setUp(){

    }

    @Test
    public void findById(){
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("FirstName1")
                .lastName("LastName1")
                .fullTime(Boolean.TRUE)
                .salary(BigDecimal.valueOf(25000))
                .departmentId(1)
                .build()));
        Employee employee = systemUnderTest.getEmployeeById(1);
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(1);
        Assertions.assertAll(
                () -> Assertions.assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> Assertions.assertNotNull(optionalEmployeeDatabaseEntry.get()),
                () -> Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("FirstName1", employee.getFirstName()),
                () -> Assertions.assertEquals("LastName1", employee.getLastName()),
                () -> Assertions.assertEquals(Boolean.TRUE, employee.getFullTime()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(25000), employee.getSalary()),
                () -> Assertions.assertEquals(1, employee.getDepartmentId())
        );
        verify(employeeDao, times(2)).findById(1);
    }

    @Test
    public void testGetAllEmployees(){
    when(employeeDao.findAll()).thenReturn(Arrays.asList(EmployeeDatabaseEntry.builder()
            .employeeId(1)
            .firstName("FirstName1")
            .lastName("LastName1")
            .fullTime(Boolean.TRUE)
            .salary(BigDecimal.valueOf(25000))
            .departmentId(1)
            .build()));

    List<Employee> employeeList = systemUnderTest.getAllEmployees();
    Assertions.assertAll(
            () -> assertNotNull(employeeList),
            () -> assertEquals(1,employeeList.size())
    );
    }

    @Test
    public void testCreateEmployee(){
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
                () -> assertEquals(employee.getEmployeeId(), createdEmployee.getEmployeeId()),
                () -> assertEquals(employee.getFirstName(), createdEmployee.getFirstName()),
                () -> assertEquals(employee.getLastName(), createdEmployee.getLastName()),
                () -> assertEquals(employee.getFullTime(), createdEmployee.getFullTime()),
                () -> assertEquals(employee.getSalary(), createdEmployee.getSalary()),
                () -> assertEquals(employee.getDepartmentId(), createdEmployee.getDepartmentId())

        );
        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));

        
    }
}
