package se.jocke.employee.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.jocke.config.H2JpaConfig;
import se.jocke.config.LiquibaseConfigurer;
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
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
public class TestEmployeeDao {
    @Autowired
    EmployeeDao employeeDao;

    private final EmployeeDatabaseEntry EMPLOYEE = EmployeeDatabaseEntryTestBuilder.build();
    private final EmployeeDatabaseEntry EMPLOYEE_DATABASE_ENTRY = EmployeeDatabaseEntryTestBuilder.build();

    @Test
    public void testGetEmployeeById() {
        Integer employeeId = 1;
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(employeeId);
        Assertions.assertAll(
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> assertNotNull(optionalEmployeeDatabaseEntry.get()),
                () -> assertEquals("Runar", optionalEmployeeDatabaseEntry.get().getFirstName()),
                () -> assertEquals(employeeId, optionalEmployeeDatabaseEntry.get().getEmployeeId()),
                () -> assertEquals("Sopranos", optionalEmployeeDatabaseEntry.get().getLastName()),
                () -> assertEquals(true, optionalEmployeeDatabaseEntry.get().getFullTime()),
                () -> assertEquals(new BigDecimal(25000), optionalEmployeeDatabaseEntry.get().getSalary().setScale(0, RoundingMode.HALF_UP)),
                () -> assertEquals(1, optionalEmployeeDatabaseEntry.get().getDepartmentId()));


    }

    @Test
    public void testCreateEmployee() {
        EmployeeDatabaseEntry EmployeeDatabaseEntry = employeeDao.save(EMPLOYEE_DATABASE_ENTRY);
        Assertions.assertAll(
                () -> assertNotNull(EmployeeDatabaseEntry.getEmployeeId()),
                () -> assertEquals(EMPLOYEE.getFirstName(), EmployeeDatabaseEntry.getFirstName()),
                () -> assertEquals(EMPLOYEE.getEmployeeId(), EmployeeDatabaseEntry.getEmployeeId()),
                () -> assertEquals(EMPLOYEE.getLastName(), EmployeeDatabaseEntry.getLastName()),
                () -> assertEquals(EMPLOYEE.getFullTime(), EmployeeDatabaseEntry.getFullTime()),
                () -> assertEquals(EMPLOYEE.getSalary(), EmployeeDatabaseEntry.getSalary().setScale(0, RoundingMode.HALF_UP)),
                () -> assertEquals(EMPLOYEE.getDepartmentId(), EmployeeDatabaseEntry.getDepartmentId()));
    }

    @Test
    public void testGetEmployees() {
        List<EmployeeDatabaseEntry> employees = employeeDao.findAll();
        Assertions.assertAll(
                () -> assertNotNull(employees),
                () -> assertEquals(3,employees.size())
        );

    }
}
