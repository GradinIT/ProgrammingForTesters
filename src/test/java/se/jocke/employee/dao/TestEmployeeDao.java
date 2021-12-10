package se.jocke.employee.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.jocke.config.PersistenceConfig;
import se.jocke.config.LiquibaseConfigurer;
import se.jocke.config.PersistenceConfig;
import se.jocke.department.dao.DepartmentDatabaseEntry;
import se.jocke.employee.Builder.EmployeeDatabaseEntryTestBuilder;
import se.jocke.employee.entity.EmployeeID;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, PersistenceConfig.class})
public class TestEmployeeDao {

    private final EmployeeDatabaseEntry EMPLOYEE_DATABASE_ENTRY = EmployeeDatabaseEntryTestBuilder.build();

    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void testGetEmployeeById() {
        employeeDao.save(EMPLOYEE_DATABASE_ENTRY);
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(EMPLOYEE_DATABASE_ENTRY.getEmployeeId());
        Assertions.assertAll(
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> assertNotNull(optionalEmployeeDatabaseEntry.get()),
                () -> assertEquals(EMPLOYEE_DATABASE_ENTRY.getFirstName(), optionalEmployeeDatabaseEntry.get().getFirstName()),
                () -> assertEquals(EMPLOYEE_DATABASE_ENTRY.getEmployeeId(), optionalEmployeeDatabaseEntry.get().getEmployeeId()),
                () -> assertEquals(EMPLOYEE_DATABASE_ENTRY.getLastName(), optionalEmployeeDatabaseEntry.get().getLastName()),
                () -> assertEquals(EMPLOYEE_DATABASE_ENTRY.getFullTime(),optionalEmployeeDatabaseEntry.get().getFullTime()),
                () -> assertEquals(EMPLOYEE_DATABASE_ENTRY.getSalary(), optionalEmployeeDatabaseEntry.get().getSalary().setScale(0, RoundingMode.HALF_UP)),
                () -> assertEquals(EMPLOYEE_DATABASE_ENTRY.getDepartmentId(), optionalEmployeeDatabaseEntry.get().getDepartmentId()));
        employeeDao.delete(EMPLOYEE_DATABASE_ENTRY);


    }

    @Test
    public void testCreateEmployee() {
        EmployeeDatabaseEntry EmployeeDatabaseEntry = employeeDao.save(EMPLOYEE_DATABASE_ENTRY);
        assertAll(
                () -> assertNotNull(EmployeeDatabaseEntry.getEmployeeId()),
                () -> assertEquals(EMPLOYEE_DATABASE_ENTRY.getFirstName(), EmployeeDatabaseEntry.getFirstName()),
                () -> assertEquals(EMPLOYEE_DATABASE_ENTRY.getEmployeeId(), EmployeeDatabaseEntry.getEmployeeId()),
                () -> assertEquals(EMPLOYEE_DATABASE_ENTRY.getLastName(), EmployeeDatabaseEntry.getLastName()),
                () -> assertEquals(EMPLOYEE_DATABASE_ENTRY.getFullTime(), EmployeeDatabaseEntry.getFullTime()),
                () -> assertEquals(EMPLOYEE_DATABASE_ENTRY.getSalary(), EmployeeDatabaseEntry.getSalary().setScale(0, RoundingMode.HALF_UP)),
                () -> assertEquals(EMPLOYEE_DATABASE_ENTRY.getDepartmentId(), EmployeeDatabaseEntry.getDepartmentId()));
        employeeDao.delete(EMPLOYEE_DATABASE_ENTRY);
    }

    @Test
    public void testDeleteEmployee() {
        employeeDao.save(EMPLOYEE_DATABASE_ENTRY);
        assertAll(
                () -> assertEquals(Boolean.TRUE, employeeDao.findById(EMPLOYEE_DATABASE_ENTRY.getEmployeeId()).isPresent()),
                () -> assertEquals(EMPLOYEE_DATABASE_ENTRY,employeeDao.findById(EMPLOYEE_DATABASE_ENTRY.getEmployeeId()).get())
        );
        employeeDao.delete(EMPLOYEE_DATABASE_ENTRY);
        assertEquals(Boolean.TRUE, employeeDao.findById(EMPLOYEE_DATABASE_ENTRY.getEmployeeId()).isEmpty());
    }
    @Test
    public void testUpdateEmployee() {
        employeeDao.save(EMPLOYEE_DATABASE_ENTRY);
        assertAll(
                () -> assertEquals(Boolean.TRUE, employeeDao.findById(EMPLOYEE_DATABASE_ENTRY.getEmployeeId()).isPresent()),
                () -> assertEquals(EMPLOYEE_DATABASE_ENTRY, employeeDao.findById(EMPLOYEE_DATABASE_ENTRY.getEmployeeId()).get())
        );
        EmployeeDatabaseEntry update = EmployeeDatabaseEntry.builder()
                .employeeId(EMPLOYEE_DATABASE_ENTRY.getEmployeeId())
                .firstName("InteRunar")
                .build();
        employeeDao.save(update);
        EmployeeDatabaseEntry updated = employeeDao.findById(EMPLOYEE_DATABASE_ENTRY.getEmployeeId()).get();
        assertAll(
                () -> assertEquals(Boolean.TRUE, employeeDao.findById(EMPLOYEE_DATABASE_ENTRY.getEmployeeId()).isPresent()),
                () -> assertEquals(update,updated)
        );
        employeeDao.delete(EMPLOYEE_DATABASE_ENTRY);
    }

    @Test
    public void testGetEmployees() {
        List<EmployeeDatabaseEntry> employees = employeeDao.findAll();
        Assertions.assertAll(
                () -> assertNotNull(employees),
                () -> assertEquals(2,employees.size())
        );

    }
}
