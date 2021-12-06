package se.jocke.employee.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.jocke.config.H2JpaConfig;
import se.jocke.config.LiquibaseConfigurer;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.math.BigDecimal;
import java.util.Optional;

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
            () -> assertEquals(employeeId, optionalEmployeeDatabaseEntry.get().getEmployeeId()),
            () -> assertEquals("firstName1", optionalEmployeeDatabaseEntry.get().getFirstName()),
            () -> assertEquals("lastName1", optionalEmployeeDatabaseEntry.get().getLastName()),
            () -> assertEquals(true, optionalEmployeeDatabaseEntry.get().getFullTime()),
            () -> assertEquals(new BigDecimal(25000.00).stripTrailingZeros(), optionalEmployeeDatabaseEntry.get().getSalary().stripTrailingZeros()),
            () -> assertEquals(1, optionalEmployeeDatabaseEntry.get().getDepartmentId())
        );
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
