package se.jocke.employee.dao;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.jocke.config.H2JpaConfig;
import se.jocke.config.LiquibaseConfigurer;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
//
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
public class TestEmployeeDao {
    @Autowired
    EmployeeDao employeeDao;

    @Test
    @DisplayName("Test find employee by ID in DB")
    public void testGetEmployeeById() {
        Integer employeeId = 1;
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(employeeId);
        Assertions.assertAll(
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> assertEquals(1, optionalEmployeeDatabaseEntry.get().getEmployeeId()),
                () -> assertEquals("firstName1", optionalEmployeeDatabaseEntry.get().getFirstName()),
                () -> assertEquals("lastName1", optionalEmployeeDatabaseEntry.get().getLastName()),
                () -> assertTrue(optionalEmployeeDatabaseEntry.get().getFullTime()),
                () -> assertEquals(new BigDecimal(25000.00).stripTrailingZeros(), optionalEmployeeDatabaseEntry.get().getSalary().stripTrailingZeros()),
                //() -> assertEquals(new BigDecimal(25000), optionalEmployeeDatabaseEntry.get().getSalary().setScale(0, RoundingMode.HALF_UP)),
                () -> assertEquals(1, optionalEmployeeDatabaseEntry.get().getDepartmentId()));
    }

    @Test
    @DisplayName("Test get all employees from DB")
    public void testGetEmployees() {
        List<EmployeeDatabaseEntry> employees = employeeDao.findAll();
        Assertions.assertAll(
                () -> assertNotNull(employees),
                () -> assertEquals(3, employees.size())
        );
    }

    @Test
    @DisplayName("Test employee exist by ID in DB")
    public void employeeExistById() {
        Integer employeeId = 3;
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(employeeId);
        Assumptions.assumeTrue(employeeDao.count() > 0);
        Assertions.assertEquals(employeeId, optionalEmployeeDatabaseEntry.get().getEmployeeId());
    }

    @Test
    @DisplayName("Test delete employee by ID from DB")
    public void testDeleteEmployeeById() {
        Integer employeeId = 3;
        Assumptions.assumeTrue(employeeDao.count() > 0);
        Assertions.assertEquals(3, employeeDao.count());
        employeeDao.deleteById(employeeId);
        Assertions.assertEquals(2, employeeDao.count());
    }

    @Test
    @DisplayName("Test delete ALL employees from DB")
    public void testDeleteAllEmployees() {
        Assumptions.assumeTrue(employeeDao.count() > 0);
        employeeDao.deleteAll();
        Assertions.assertEquals(0, employeeDao.count());
    }
}
