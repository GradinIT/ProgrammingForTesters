package se.jensen;

import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.jensen.dao.EmployeeDao;
import se.jensen.dao.EmployeeFakeDao;
import se.jensen.dao.EntityNotFoundException;
import se.jensen.entity.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;


public class EmployeeDaoTest {
    private EmployeeFakeDao employeeDao;
    private Map<Integer, Employee> storage = Maps.newHashMap();

    @Before
    public void before() {
        employeeDao = new EmployeeFakeDao(storage);
        employeeDao.setTestData();
    }

    @Test(expected = EntityNotFoundException.class)
    public void testThatEntityNotFoundIsThrownWhenEntityNotPresent() {
        employeeDao.getEmployee(8);
    }

    @Test
    public void testThatAllEntitiesIsInSearchResult() {
        Collection<Employee> employees = employeeDao.getAllEmployees();
        Assert.assertNotNull(employees);
        Assert.assertEquals(2,employees.size());
    }
}
