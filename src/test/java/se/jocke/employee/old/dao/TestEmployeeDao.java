package se.jocke.employee.old.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.jocke.config.H2JpaConfig;
import se.jocke.config.LiquibaseConfigurer;
import se.jocke.employee.dao.EmployeeDao;
import se.jocke.employee.dao.EmployeeDatabaseEntry;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
public class TestEmployeeDao {
    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void testGetEmployeeById() {
        Integer employeeId = 1;
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(employeeId);
        Assertions.assertAll(
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> assertNotNull(optionalEmployeeDatabaseEntry.get()),
                () -> assertEquals("firstName1", optionalEmployeeDatabaseEntry.get().getFirstName()),
                () -> assertEquals(employeeId, optionalEmployeeDatabaseEntry.get().getEmployeeId())
        );
    }

    @Test
    public void testGetDepartments() {
        List<EmployeeDatabaseEntry> employee = employeeDao.findAll();
        Assertions.assertAll(
                () -> assertNotNull(employee),
                () -> assertEquals(3, employee.size())
        );
    }
}
