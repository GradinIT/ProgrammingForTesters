package se.jocke.employee.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.jocke.H2JpaConfig;
import se.jocke.LiquibaseConfigurer;
import se.jocke.api.EmployeeModel;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Oscar Bergström 02-03-2021
 */

// Start a Spring Boot context for testing a Liquibase dependent component
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
public class TestEmployeeDao {

    // Dependency inject EmployeeDao
    @Autowired
    EmployeeDao employeeDao;

    // Test that the database service contains an Employee matching what we expect for Employee #1
    @Test
    public void testGetEmployeeById() {
        Integer employeeId = 1;
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(employeeId);
        Assertions.assertAll(
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> assertNotNull(optionalEmployeeDatabaseEntry.get()),
                () -> assertEquals("firstName1", optionalEmployeeDatabaseEntry.get().getFirstName()),
                () -> assertEquals("lastName1", optionalEmployeeDatabaseEntry.get().getLastName()),
                () -> assertEquals(employeeId, optionalEmployeeDatabaseEntry.get().getEmployeeId())
        );
    }

    // Test that the database service contains the number of Employees we expect
    @Test
    public void testGetEmployees() {
        List<EmployeeDatabaseEntry> employeeList = employeeDao.findAll();
        Assertions.assertAll(
                () -> assertNotNull(employeeList),
                () -> assertEquals(3, employeeList.size())
        );
    }
}
