package se.jocke.employee.dao;

import liquibase.Liquibase;
import liquibase.pro.packaged.L;
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
import se.jocke.department.entity.Employee;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
public class TestEmployeeDao {

    @Autowired
    private EmployeeDao employeeDao;

    @Test
    public void testGetEmployeesByName() {
        List<EmployeeDatabaseEntry> data = employeeDao.findAll();

        Assertions.assertAll(
                () -> assertNotNull(data),
                () -> assertEquals(1, data
                        .stream()
                        .filter(D -> D.getFirstName().equals("Anita")).count())
        );
    }

    @Test
    public void testGetEmployeesWithoutFulltime() {
        List<EmployeeDatabaseEntry> data = employeeDao.findAll();

        Assertions.assertAll(
                () -> assertNotNull(data),
                () -> assertEquals(2,data
                        .stream()
                        .filter(EmployeeDatabaseEntry::getFullTime).count())
        );
    }

    @Test
    public void testGetEmployeeById() {
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(3);

        Assertions.assertAll(
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> assertNotNull(optionalEmployeeDatabaseEntry),
                () -> assertEquals("Anita",optionalEmployeeDatabaseEntry.get().getFirstName())
        );
    }

    @Test
    public void getAllEmployeesByDepartmentId() {
        List<EmployeeDatabaseEntry> data = employeeDao.findAll();

        Assertions.assertAll(
                () -> assertFalse(data.isEmpty()),
                () -> assertEquals(2,data.stream()
                        .filter(D -> D.getDepartmentId() == 2)
                        .count())
        );
    }

    @Test
    public void getEmployeesBetweenSalaryRange() {
        List<EmployeeDatabaseEntry> data = employeeDao.findAll();

        Predicate<EmployeeDatabaseEntry> salaryRange = D -> D.getSalary().compareTo(new BigDecimal("25000")) > 0 && D.getSalary().compareTo(new BigDecimal("27000")) < 0;

        Assertions.assertAll(
                () -> assertFalse(data.isEmpty()),
                () -> assertEquals(1,data.stream()
                        .filter(salaryRange)
                        .count())
        );
    }

}
