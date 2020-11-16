package se.jensen.exercise;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.jensen.dao.EmployeeDao;
import se.jensen.entity.Employee;
import se.jensen.service.EmployeeService;
import se.jensen.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestEmployeeService {

    EmployeeDao employeeDao = mock(EmployeeDao.class);

    EmployeeService employeeService = new EmployeeServiceImpl(employeeDao);

    @Before
    public void setUp() {
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

        when(employeeDao.getAllEmployees()).thenReturn(employees);
    }

    @Test
    public void testGetAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        Assert.assertEquals(2, employees.size());
        verify(employeeDao, times(1)).getAllEmployees();
        Employee employeeAllan = employees.get(0);
        Assert.assertEquals("Allan", employeeAllan.getFirstName());
    }
}
