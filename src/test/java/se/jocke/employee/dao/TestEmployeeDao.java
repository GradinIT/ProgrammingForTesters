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
    @DisplayName("Test find employee by ID")
    public void testEmployeeFindById() {
        Integer employeeId = 3;
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(employeeId);
        Assumptions.assumeTrue(employeeDao.count() > 0);
        Assertions.assertEquals(employeeId, optionalEmployeeDatabaseEntry.get().getEmployeeId());
    }

    @Test
    @DisplayName("Test employee exist by ID")
    public void testEmployeeExistById() {
        Assumptions.assumeTrue(employeeDao.count() > 0);
        Assertions.assertTrue(employeeDao.existsById(1));
    }

    @Test
    @DisplayName("Test create and delete employee by ID from DB")
    public void testCreateAndDeleteEmployeeById() {
        Integer employeeId = 4;
        Long numberOfEmp = employeeDao.count();

        EmployeeDatabaseEntry newEmployee = EmployeeDatabaseEntry.builder()
                .employeeId(employeeId)
                .firstName("firstname4")
                .lastName("lastname4")
                .salary(new BigDecimal(25000))
                .fullTime(true)
                .build();
        employeeDao.save(newEmployee);

        Assertions.assertEquals(numberOfEmp + 1, employeeDao.count());
        employeeDao.deleteById(4);
        Assertions.assertEquals(numberOfEmp, employeeDao.count());
    }

    @Test
    @DisplayName("Test delete and restore ALL employees in DB")
    public void testDeleteAndRestoreAllEmployees() {
        // Get number of DB entries
        Long numberOfEmployees = employeeDao.count();
        // Create new list with existing employee
        List<EmployeeDatabaseEntry> captureEmployeesInDB = employeeDao.findAll();

        // Delete all in list
        Assumptions.assumeTrue(employeeDao.count() > 0);
        employeeDao.deleteAll();
        Assertions.assertEquals(0, employeeDao.count());

        // Restore DB with extracted employees
        employeeDao.saveAll(captureEmployeesInDB);

        Assertions.assertEquals(numberOfEmployees, employeeDao.count());
    }
}
