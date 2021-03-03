package se.jocke.employee.service;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.department.entity.Employee;
import se.jocke.department.entity.Entity;
import se.jocke.department.entity.EntityID;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TestEmployeeService {
    @Mock
    private EmployeeDao employeeDao;
    @Mock
    private Employee e;
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
/*
    @BeforeEach
    public void setUpGetAll(){
        when(employeeDao.findAll()).thenReturn(List<EmployeeDao>);
    }*/

    @Test
    public void findById() {
        Employee employee = systemUnderTest.getEmployeeById(1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("firstName1", employee.getFirstName()),
                () -> Assertions.assertEquals("lastName1", employee.getLastName()),
                () -> Assertions.assertEquals(true, employee.getFullTime()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(25000), employee.getSalary()),
                () -> Assertions.assertEquals(1, employee.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(1);
    }

    @Test
    public void createOrUpdateEmployee(){
        when(e.getEmployeeId().getId()).thenReturn(5);
        when(e.getFirstName()).thenReturn("firstName1");
        when(e.getLastName()).thenReturn("lastName2");
        when(e.getFullTime()).thenReturn(true);
        when(e.getSalary()).thenReturn(BigDecimal.valueOf(25000));
        when(e.getDepartmentId()).thenReturn(1);
        Employee employee = systemUnderTest.createEmployee(e);
        Assertions.assertAll(
                () -> Assertions.assertEquals(5, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("firstName1", employee.getFirstName()),
                () -> Assertions.assertEquals("lastName1", employee.getLastName()),
                () -> Assertions.assertEquals(Boolean.TRUE, employee.getFullTime()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(25000), employee.getSalary()),
                () -> Assertions.assertEquals(1, employee.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(1);
    }

    @Test
    public void remove(){
        Employee employee = systemUnderTest.removeEmployee(systemUnderTest.getEmployeeById(1));
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("firstName1", employee.getFirstName()),
                () -> Assertions.assertEquals("lastName1", employee.getLastName()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(25000), employee.getSalary()),
                () -> Assertions.assertEquals(Boolean.TRUE, employee.getFullTime()),
                () -> Assertions.assertEquals(1, employee.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(1);
    }

    @Test
    public void update(){

    }

    @Test
    public void getAll(){
        List<Employee> employees = systemUnderTest.getAllEmployees();
        verify(employeeDao, times(1)).findAll();
    }

/*    Employee createOrUpdateEmployee(Employee employee);

    Employee removeEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    List<Employee> getAllEmployees();*/

}
