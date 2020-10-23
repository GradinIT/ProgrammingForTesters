package se.jensen.exercise;

import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import se.jensen.dao.EmployeeFakeDao;
import se.jensen.dao.EntityAlreadyInStorageException;
import se.jensen.dao.EntityNotFoundException;
import se.jensen.entity.Employee;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.fail;

public class EmployeeDaoTest {
    private EmployeeFakeDao employeeDao;
    private Map<Integer, Employee> storage = Maps.newHashMap();

    @Before
    public void before() {
        employeeDao = new EmployeeFakeDao(storage);
        employeeDao.setTestData();
    }

    @Test
    public void testThatEntityNotFoundExceptionIsThrown() {
        try {
            employeeDao.getEmployee(10);
            fail(); //TODO: if we dont get an exception then fail
        }
        catch (EntityNotFoundException e) {

        }
        catch (Exception e) {
            fail(); //TODO: if we get any other exeption then fail
        }
    }

    @Test(expected = EntityAlreadyInStorageException.class)
    public void testThatEntityAlreadyInStorageExceptionIsThrown() {
        Employee employeeAlreadyInStorage = storage.get(1);
        employeeDao.create(employeeAlreadyInStorage);
    }

    @Test
    public void testThatNewEmployeeIsStore(){
        Integer employeeId = 10;
        String firstName = "firstName";
        String lastName = "lastName";
        BigDecimal salary = BigDecimal.valueOf(10000);
        Boolean fullTime = Boolean.TRUE;

        Employee employee = Employee.builder()
                .setEmployeeId(employeeId)
                .setLastName(lastName)
                .setFirstName(firstName)
                .setFullTime(fullTime)
                .setSalary(salary)
                .build();

        Employee employeeCreated = employeeDao.create(employee);
        Employee employeeInStorage = storage.get(employeeId);
        Assert.assertNotNull(employeeInStorage);
        Assert.assertEquals(employeeCreated,employeeInStorage);
        Assert.assertEquals(employee,employeeCreated);
    }



}
