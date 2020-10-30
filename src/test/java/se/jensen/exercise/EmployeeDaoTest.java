package se.jensen.exercise;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import se.jensen.H2JpaConfig;
import se.jensen.LiquibaseConfigurer;
import se.jensen.dao.EmployeeDao;
import se.jensen.dao.mapper.EmployeePojoMapper;
import se.jensen.entity.Employee;
import se.jensen.entity.EmployeeTestBuilder;

import javax.ws.rs.core.Application;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class, LiquibaseConfigurer.class, H2JpaConfig.class})
public class EmployeeDaoTest {

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void testSaveNewEmployee() {
        Employee employee = EmployeePojoMapper.map(employeeDao.save(EmployeePojoMapper.map(
                EmployeeTestBuilder.build())));

        List<Employee> employees = EmployeePojoMapper.map(employeeDao.findAll());
        for(Employee e: employees)
            System.out.println(e);
    }
}