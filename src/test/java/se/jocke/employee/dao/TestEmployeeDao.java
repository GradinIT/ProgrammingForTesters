package se.jocke.employee.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.jocke.config.H2JpaConfig;
import se.jocke.config.LiquibaseConfigurer;
import se.jocke.department.dao.DepartmentDao;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
public class TestEmployeeDao {
    @Autowired
    DepartmentDao departmentDao;

    @Test
    public void testGetEmployeeById() {
        // comment -> 03dec 0953
    }

    @Test
    public void testGetEmployees() {

    }

    @Test
    public void testGetEmployeesByFullName() {

    }

    @Test
    public void testGetEmployeeByFirstName() {

    }

    @Test
    public void testGetEmployeeByLastName() {

    }

    @Test
    public void testGetEmployeesByFullTime() {

    }

    @Test
    public void testGetEmployeeByDepartmentId() {

    }

    @Test
    public void testGetAllDepartments() {

    }
}
