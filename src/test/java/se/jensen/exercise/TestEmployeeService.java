package se.jensen.exercise;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.jensen.dao.EmployeeDao;
import se.jensen.dao.EmployeeDatabaseEntry;
import se.jensen.entity.Employee;
import se.jensen.service.EmployeeService;
import se.jensen.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestEmployeeService {

    EmployeeDao employeeDao = mock(EmployeeDao.class);
    EmployeeService service = new EmployeeServiceImpl(employeeDao);

    private final Integer EMPLOYEEID = 10;
    private final String FIRSTNAME = "Arne";
    private final String LASTNAME = "Andersson";
    private final Boolean FULLTIME = true;
    private final BigDecimal SALARY = BigDecimal.valueOf(25000.0);

    @Before
    public void setUp() {
        EmployeeDatabaseEntry employeeDatabaseEntry = EmployeeDatabaseEntry.builder()
                .employeeId(EMPLOYEEID)
                .firstName(FIRSTNAME)
                .lastName(LASTNAME)
                .salary(SALARY)
                .fullTime(FULLTIME)
                .build();
        List<EmployeeDatabaseEntry> list = new ArrayList<>();
        list.add(employeeDatabaseEntry);

        when(employeeDao.findAll()).thenReturn(list);


    }

    @Test
    public void testGetAll() {
        List<Employee> all = service.getAllEmployees();
        verify(employeeDao,times(1)).findAll();
        Assert.assertNotNull(all);
        Assert.assertEquals(1,all.size());
    }
    @Test
    public void testGetById() {
        Integer employeeId = Integer.valueOf(15);

        EmployeeDatabaseEntry employeeDatabaseEntry = EmployeeDatabaseEntry.builder() // setup test reply from database
                .employeeId(employeeId)
                .firstName(FIRSTNAME)
                .lastName(LASTNAME)
                .salary(SALARY)
                .fullTime(FULLTIME)
                .build();

        when(employeeDao.findById(employeeId)).thenReturn(Optional.of(employeeDatabaseEntry)); // Mock the call

        Employee employee = service.getEmployeeById(employeeId);
        verify(employeeDao,times(1)).findById(employeeId);
        Assert.assertNotNull(employee);
        Assert.assertEquals(FIRSTNAME,employee.getFirstName());
        Assert.assertEquals(LASTNAME,employee.getLastName());
        Assert.assertEquals(FULLTIME,employee.getFullTime());
        Assert.assertEquals(SALARY,employee.getSalary());
        Assert.assertEquals(employeeId,employee.getEmployeeId());
    }
}
