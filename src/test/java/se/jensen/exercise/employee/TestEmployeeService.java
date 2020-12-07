package se.jensen.exercise.employee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import se.jensen.dao.EmployeeDao;
import se.jensen.dao.EmployeeDatabaseEntry;
import se.jensen.entity.Employee;
import se.jensen.exercise.test.builder.EmployeeTestBuilder;
import se.jensen.service.EmployeeService;
import se.jensen.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestEmployeeService {

    EmployeeDao employeeDao = mock(EmployeeDao.class);
    @InjectMocks
    EmployeeService service = new EmployeeServiceImpl();

    private final Integer EMPLOYEEID = 10;
    private final String FIRSTNAME = "Arne";
    private final String LASTNAME = "Andersson";
    private final Boolean FULLTIME = true;
    private final BigDecimal SALARY = BigDecimal.valueOf(25000.0);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        EmployeeDatabaseEntry employeeDatabaseEntry = EmployeeDatabaseEntry.builder()
                .employeeId(EMPLOYEEID)
                .firstName(FIRSTNAME)
                .lastName(LASTNAME)
                .salary(SALARY)
                .fullTime(FULLTIME)
                .departmentId(1)
                .build();
        List<EmployeeDatabaseEntry> list = new ArrayList<>();
        list.add(employeeDatabaseEntry);

        when(employeeDao.findAll()).thenReturn(list);

    }

    @Test
    public void testGetAll() {
        List<Employee> all = service.getAllEmployees();
        verify(employeeDao, times(1)).findAll();
        Assert.assertNotNull(all);
        Assert.assertEquals(1, all.size());
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
                .departmentId(1)
                .build();

        when(employeeDao.findById(employeeId)).thenReturn(Optional.of(employeeDatabaseEntry)); // Mock the call

        Employee employee = service.getEmployeeById(employeeId);
        verify(employeeDao, times(1)).findById(employeeId);
        Assert.assertNotNull(employee);
        Assert.assertEquals(FIRSTNAME, employee.getFirstName());
        Assert.assertEquals(LASTNAME, employee.getLastName());
        Assert.assertEquals(FULLTIME, employee.getFullTime());
        Assert.assertEquals(SALARY, employee.getSalary());
        Assert.assertEquals(employeeId, employee.getEmployeeId().getId());
    }

    @Test
    public void testDeleteEmployee() {
        service.removeEmployee(EmployeeTestBuilder.build());
        verify(employeeDao, atLeastOnce()).delete(any());
    }
}
