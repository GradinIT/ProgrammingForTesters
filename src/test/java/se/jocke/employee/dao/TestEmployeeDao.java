package se.jocke.employee.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.jocke.config.H2JpaConfig;
import se.jocke.config.LiquibaseConfigurer;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
public class TestEmployeeDao {
    @Autowired
    EmployeeDao employeeDao;

    @Test
    @DisplayName("Test find employee by ID in DB")
    public void testGetEmployeeById() {
    }

    @Test
    @DisplayName("Test get all employees from DB")
    public void testGetEmployees() {

    }

    @Test
    @DisplayName("Test employee exist by ID in DB")
    public void employeeExistById() {
    }

    @Test
    @DisplayName("Test delete employee by ID from DB")
    public void testDeleteEmployeeById() {
    }

    @Test
    @DisplayName("Test delete ALL employees from DB")
    public void testDeleteAllEmployees() {
    }
}
