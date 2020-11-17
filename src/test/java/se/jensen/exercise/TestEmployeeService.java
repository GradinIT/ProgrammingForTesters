package se.jensen.exercise;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
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


    private EmployeeDao employeeDao = mock(EmployeeDao.class);


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

        when(employeeDao.getAllEmployees()).thenReturn(employees);
    }

    @Test
    public void testGetAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        Assert.assertEquals(2, employees.size());
        verify(employeeDao, times(1)).getAllEmployees();
        Employee employeeAllan = employees.get(0);
        Assert.assertEquals("Allan", employeeAllan.getFirstName());
        Assert.assertEquals("Edvall", employeeAllan.getLastName());
        Assert.assertEquals(BigDecimal.valueOf(10000), employeeAllan.getSalary());
        Assert.assertEquals(true, employeeAllan.getFullTime());
        Assert.assertEquals(Integer.valueOf(1), employeeAllan.getEmployeeId());
    }
}
