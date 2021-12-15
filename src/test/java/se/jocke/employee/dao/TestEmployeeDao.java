package se.jocke.employee.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.jocke.config.H2JpaConfig;
import se.jocke.config.LiquibaseConfigurer;
import se.jocke.employee.builder.EmployeeDatabaseEntryTestBuilder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
public class TestEmployeeDao {
    private final EmployeeDatabaseEntry ENTRY = EmployeeDatabaseEntryTestBuilder.build();

    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void testGetEmployeeById() {
        employeeDao.save(ENTRY);
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(ENTRY.getEmployeeId());
        Assertions.assertAll(
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> assertNotNull(optionalEmployeeDatabaseEntry.get()),
                () -> assertEquals("Test/QA", optionalEmployeeDatabaseEntry.get().getFirstName()),
                () -> assertEquals("SQUARE1", optionalEmployeeDatabaseEntry.get().getLastName()),
                () -> assertEquals(ENTRY.getEmployeeId(), optionalEmployeeDatabaseEntry.get().getEmployeeId())

        );
        employeeDao.delete(ENTRY);
    }

    @Test
    public void testGetEmployees() {

        List<EmployeeDatabaseEntry> employees = employeeDao.findAll();
        Assertions.assertAll(
                () -> assertNotNull(employees),
                () -> assertEquals(3, employees.size())
        );

    }

    @Test
    public void testStoreEmployee() {
        employeeDao.save(ENTRY);
        Assertions.assertAll(
                () -> assertEquals(Boolean.TRUE, employeeDao.findById(ENTRY.getEmployeeId()).isPresent()),
                () -> assertEquals(ENTRY, employeeDao.findById(ENTRY.getEmployeeId()).get())
        );
        employeeDao.delete(ENTRY);
    }

    @Test
    public void testDeleteEmployee() throws InterruptedException {
        employeeDao.save(ENTRY);
        Assertions.assertAll(
                () -> assertEquals(Boolean.TRUE, employeeDao.findById(ENTRY.getEmployeeId()).isPresent()),
                () -> assertEquals(ENTRY, employeeDao.findById(ENTRY.getEmployeeId()).get())
        );
        employeeDao.delete(ENTRY);
        Assertions.assertEquals(Boolean.TRUE, employeeDao.findById(ENTRY.getEmployeeId()).isEmpty());

    }
    @Test
    public void testUpdateEmployee() {
        employeeDao.save(ENTRY);
        Assertions.assertAll(
                () -> assertEquals(Boolean.TRUE, employeeDao.findById(ENTRY.getEmployeeId()).isPresent()),
                () -> assertEquals(ENTRY, employeeDao.findById(ENTRY.getEmployeeId()).get())
        );
        EmployeeDatabaseEntry update = EmployeeDatabaseEntry.builder()
                .employeeId(ENTRY.getEmployeeId())
                .firstName("Tok")
                .build();
        employeeDao.save(update);
        EmployeeDatabaseEntry updated = employeeDao.findById(ENTRY.getEmployeeId()).get();
        Assertions.assertAll(
                () -> assertEquals(Boolean.TRUE, employeeDao.findById(ENTRY.getEmployeeId()).isPresent()),
                () -> assertEquals(update, updated)
        );
        employeeDao.delete(updated);
    }
}
