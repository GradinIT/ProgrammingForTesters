package se.jensen.exercise.employee;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
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
import se.jensen.entity.EmployeeID;
import se.jensen.exercise.test.builder.EmployeeTestBuilder;
import se.jensen.test.category.UnitTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
@Category(UnitTest.class)
public class EmployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;

    private Employee testEmployee = Employee.builder()
            .employeeId(EmployeeID.builder().id(20).build())
            .firstName("Runar")
            .lastName("Soergaard")
            .fullTime(true)
            .salary(BigDecimal.valueOf(25000))
            .departmentId(3)
            .build();
    @Test
    public void testSaveNewEmployee() {
        Employee employee = EmployeePojoMapper.map(employeeDao.save(EmployeePojoMapper.map(
                testEmployee)));
        Assert.assertNotNull(employee);
        Assert.assertEquals(testEmployee.getFirstName(),employee.getFirstName());
    }
    @Test
    public void testGetEmployee() {
        Optional<EmployeeDatabaseEntry> optional = employeeDao.findById(1);
        Assert.assertTrue(optional.isPresent());

        Employee employee = EmployeePojoMapper.map(optional.get());
        Assert.assertNotNull(employee);
    }
}