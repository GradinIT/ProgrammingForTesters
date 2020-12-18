package se.jensen.exercise.employee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.jensen.H2JpaConfig;
import se.jensen.LiquibaseConfigurer;
import se.jensen.dao.EmployeeDao;
import se.jensen.dao.EmployeeDatabaseEntry;
import se.jensen.dao.mapper.EmployeePojoMapper;
import se.jensen.entity.Employee;
import se.jensen.exercise.test.builder.EmployeeTestBuilder;

import javax.ws.rs.core.Application;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, LiquibaseConfigurer.class, H2JpaConfig.class})
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
        List<EmployeeDatabaseEntry> employees = employeeDao.findAll();
        Assert.assertEquals(2,employees.size());
        employees.stream().forEach(System.out::println);
    }

    @Test
    public void testSaveNewEmployee() {
        List<EmployeeDatabaseEntry> employees = employeeDao.findAll();
        Assert.assertEquals(2,employees.size());
        employeeDao.saveAndFlush(EmployeeDatabaseEntry.builder()
                .departmentId(1)
                .employeeId(15)
                .firstName("Test15")
                .lastName("Testing15")
                .salary(BigDecimal.valueOf(25000))
                .fullTime(true)
                .build());
        employees = employeeDao.findAll();
        Assert.assertEquals(3,employees.size());
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
        Assert.assertEquals(1,employees.size());
    }
    @Test
    public void testGetById() {
        Optional<EmployeeDatabaseEntry> employeeDatabaseEntry = employeeDao.findById(1);
        Assert.assertTrue(employeeDatabaseEntry.isPresent());
    }
}