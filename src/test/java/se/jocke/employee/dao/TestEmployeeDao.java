package se.jocke.employee.dao;

import liquibase.pro.packaged.D;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.jocke.H2JpaConfig;
import se.jocke.LiquibaseConfigurer;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.department.entity.Employee;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
@DisplayName("Test employee DAO (CRUD)")
public class TestEmployeeDao {

    @Autowired
    private EmployeeDao employeeDao;

    @Nested
    @DisplayName("Test to create employee")
    class testPostFunction {

        @Test
        @DisplayName("Given that we want to add an employee")
        public void testCreateEmployee() {

        }
    }

    @Nested
    @DisplayName("Test to GET employee")
    class testGetFunctions {

        @Test
        @DisplayName("Given that we want employee by id")
        public void testGetEmployeeById() {
            Optional<EmployeeDatabaseEntry> employeeByID = employeeDao.findById(1);
            Assertions.assertEquals(1, employeeByID.get().getEmployeeId());
        }

        @Test
        @DisplayName("Given that we want all employees")
        public void testGetAllEmployees() {
            List<EmployeeDatabaseEntry> allEmployees = employeeDao.findAll();
            Assertions.assertEquals(3, allEmployees.size());
        }

    }

    @Test
    public void testGetAllEmployees() {

    }

}
