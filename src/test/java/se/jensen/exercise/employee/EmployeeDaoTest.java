package se.jensen.exercise.employee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import se.jensen.H2JpaConfig;
import se.jensen.LiquibaseConfigurer;
import se.jensen.dao.EmployeeDao;
import se.jensen.dao.EmployeeDatabaseEntry;
import se.jensen.entity.EmployeeID;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes={LiquibaseConfigurer.class, H2JpaConfig.class})
public class EmployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Before
    public void setUp() {
        employeeDao.deleteAll();
        employeeDao.saveAll(Arrays.asList(EmployeeDatabaseEntry.builder()
                        .departmentId(1)
                        .employeeId(1)
                        .firstName("One")
                        .lastName("Testing")
                        .salary(BigDecimal.valueOf(25000))
                        .fullTime(true)
                        .build(),
                EmployeeDatabaseEntry.builder()
                        .departmentId(1)
                        .employeeId(2)
                        .firstName("Two")
                        .lastName("Testing")
                        .salary(BigDecimal.valueOf(25000))
                        .fullTime(true)
                        .build()));
    }

    @Test
    public void testGetAll() {
        Object employees = employeeDao.findAll();
        Assert.assertEquals(2, ((List)employees).size());
    }

    @Test
    public void testSaveNewEmployee() {
        List<EmployeeDatabaseEntry> employees = employeeDao.findAll();
        Assert.assertEquals(2, employees.size());
        employeeDao.saveAndFlush(
                EmployeeDatabaseEntry.builder()
                .departmentId(1)
                .employeeId(15)
                .firstName("Test15")
                .lastName("Testing15")
                .salary(BigDecimal.valueOf(25000))
                .fullTime(true)
                .build());
        employees = employeeDao.findAll();
        Assert.assertEquals(3, employees.size());
    }

    @Test
    public void testDeleteEmployee() {
        employeeDao.delete(EmployeeDatabaseEntry.builder()
                .departmentId(1)
                .employeeId(2)
                .firstName("Two")
                .lastName("Testing")
                .salary(BigDecimal.valueOf(25000))
                .fullTime(true)
                .build());
        List<EmployeeDatabaseEntry> employees = employeeDao.findAll();
        Assert.assertEquals(1, employees.size());
    }

    @Test
    public void testGetById() {
        Optional<EmployeeDatabaseEntry> employeeDatabaseEntry = employeeDao.findById(1);
        Assert.assertTrue(employeeDatabaseEntry.isPresent());
    }
}