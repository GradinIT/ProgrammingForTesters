package se.jocke.employee.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.jocke.H2JpaConfig;
import se.jocke.LiquibaseConfigurer;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
@DisplayName("Test employee DAO")
public class TestEmployeeDao {

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    @DisplayName("Given that we want employee by id")
    public void testGetEmployeeById() {
        Optional<EmployeeDatabaseEntry> employeeByID = employeeDao.findById(1);
        Assertions.assertAll(
                () -> assertTrue(employeeByID.isPresent()),
                () -> assertEquals(1, employeeByID.get().getEmployeeId()),
                () -> assertEquals("firstName1", employeeByID.get().getFirstName()),
                () -> assertEquals("lastName1", employeeByID.get().getLastName()),
                () -> assertTrue(employeeByID.get().getFullTime()),
                () -> assertEquals(new BigDecimal("25000.00"), employeeByID.get().getSalary()),
                () -> assertEquals(1, employeeByID.get().getDepartmentId())
        );
    }

    @Test
    @DisplayName("Given that we want all employees")
    public void testGetAllEmployees() {
        List<EmployeeDatabaseEntry> allEmployees = employeeDao.findAll();
        Assertions.assertAll(
                () -> assertFalse(allEmployees.isEmpty()),
                () -> assertEquals(3, allEmployees.size())
        );
    }

}
