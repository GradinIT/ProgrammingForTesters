package se.jensen.exercise;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import se.jensen.dao.EmployeeDao;
import se.jensen.dao.EmployeeFakeDao;
import se.jensen.entity.Employee;
import se.jensen.service.EmployeeService;
import se.jensen.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestEmployeeService {

    private EmployeeDao mockDao = mock(EmployeeDao.class);

    @InjectMocks EmployeeService employeeService = new EmployeeServiceImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        List<Employee> employees = new ArrayList<>();
        employees.add(Employee.builder()
                .setEmployeeId(1)
                .setFirstName("Allan")
                .setLastName("Edvall")
                .setSalary(BigDecimal.valueOf(10000))
                .setFullTime(true)
                .build());
        employees.add(Employee.builder()
                .setEmployeeId(2)
                .setFirstName("Inga")
                .setLastName("Edvall")
                .setSalary(BigDecimal.valueOf(10000))
                .setFullTime(false).build());

        when(mockDao.getAllEmployees()).thenReturn(employees);
        when(mockDao.getEmployee(2)).thenReturn(employees.get(1));
    }

    @Test
    public void testGetAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        Assert.assertEquals(2, employees.size());
        verify(mockDao, times(1)).getAllEmployees();
        Employee employeeAllan = employees.get(0);
        Assert.assertEquals("Allan", employeeAllan.getFirstName());
        Assert.assertEquals("Edvall", employeeAllan.getLastName());
        Assert.assertEquals(BigDecimal.valueOf(10000), employeeAllan.getSalary());
        Assert.assertEquals(true, employeeAllan.getFullTime());
        Assert.assertEquals(Integer.valueOf(1), employeeAllan.getEmployeeId());
    }
    @Test
    public void testGetEmployeeById() {
        Employee employee = employeeService.getEmployeeById(2);
        Assert.assertNotNull(employee);
        Assert.assertEquals("Inga", employee.getFirstName());
        Assert.assertEquals("Edvall", employee.getLastName());
        Assert.assertEquals(BigDecimal.valueOf(10000), employee.getSalary());
        Assert.assertEquals(false, employee.getFullTime());
        Assert.assertEquals(Integer.valueOf(2), employee.getEmployeeId());
    }

    //TODO: create tests for
    /*
    Employee createOrUpdateEmployee(Employee employee);

    Employee removeEmployee(Employee employee);

    Employee updateEmployee(Employee employee);
     */
}
