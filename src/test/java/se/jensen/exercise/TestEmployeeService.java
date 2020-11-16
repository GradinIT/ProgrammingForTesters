package se.jensen.exercise;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.jensen.dao.EmployeeDao;
import se.jensen.entity.Employee;
import se.jensen.service.EmployeeService;
import se.jensen.service.EmployeeServiceImpl;

import java.util.List;

import static org.mockito.Mockito.mock;

public class TestEmployeeService {

    EmployeeDao employeeDao = mock(EmployeeDao.class);

    EmployeeService employeeService = new EmployeeServiceImpl(employeeDao);

    @Before
    public void setUp () {
        //TODO: make the mocked dao return a list of two employees
    }

    @Test
    public void testGetAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        Assert.assertEquals(2,employees.size());
        //TODO: make shure that the method findAll() is called once ( one time ) 
    }
}
