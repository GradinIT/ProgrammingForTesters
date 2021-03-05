package se.jocke.employee.dao;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
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
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
public class TestEmployeeDao {
    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void testGetEmployeeById() {
        Integer expectedEmployeeId = 1;
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(1);
        Assertions.assertAll(
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> assertNotNull(optionalEmployeeDatabaseEntry.get()),
                () -> assertEquals(expectedEmployeeId,optionalEmployeeDatabaseEntry.get().getEmployeeId())
        );
    }

    @Test
    public void testGetEmployeeByFirstName() {
        String expectedFirstName = "firstName1";
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(1);
        Assertions.assertAll(
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> assertNotNull(optionalEmployeeDatabaseEntry.get()),
                () -> assertEquals(expectedFirstName,optionalEmployeeDatabaseEntry.get().getFirstName())
        );
    }

    @Test
    public void testGetEmployeeByLastName() {
        String expectedLastName = "lastName1";
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(1);
        Assertions.assertAll(
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> assertNotNull(optionalEmployeeDatabaseEntry.get()),
                () -> assertEquals(expectedLastName,optionalEmployeeDatabaseEntry.get().getLastName())
        );
    }

    @Test
    public void testGetEmployeeBySalary() {
        BigDecimal bd1 = new BigDecimal(25000.00);
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(1);
        Assertions.assertAll(
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> assertNotNull(optionalEmployeeDatabaseEntry.get()),
                () -> assertThat(bd1, Matchers.comparesEqualTo(optionalEmployeeDatabaseEntry.get().getSalary()))
        );
    }

    @Test
    public void testGetEmployeeByFullTime() {
        Boolean expectedFullTime = true;
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(1);
        Assertions.assertAll(
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> assertNotNull(optionalEmployeeDatabaseEntry.get()),
                () -> assertEquals(expectedFullTime,optionalEmployeeDatabaseEntry.get().getFullTime())
        );
    }

    @Test
    public void testGetEmployeeByDepartmentId() {
        Integer expectedDepartmentId = 1;
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(1);
        Assertions.assertAll(
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> assertNotNull(optionalEmployeeDatabaseEntry.get()),
                () -> assertEquals(expectedDepartmentId,optionalEmployeeDatabaseEntry.get().getDepartmentId())
        );
   }
}
