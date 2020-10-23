package se.jensen.exercise;

import com.google.common.collect.Maps;
import org.junit.Before;
import se.jensen.dao.EmployeeFakeDao;
import se.jensen.entity.Employee;

import java.util.Map;

public class EmployeeDaoTest {
    private EmployeeFakeDao employeeDao;

    private Map<Integer, Employee> storage = Maps.newHashMap();

    @Before
    public void before() {
        employeeDao = new EmployeeFakeDao(storage);
        employeeDao.setTestData();
    }

}
