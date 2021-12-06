package se.jocke.employee.unittest.dao;

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

import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
                () -> assertEquals("Fagel", optionalEmployeeDatabaseEntry.get().getFirstName()),
                () -> assertEquals(employeeId,optionalEmployeeDatabaseEntry.get().getEmployeeId()),
                () -> assertEquals("Holk",optionalEmployeeDatabaseEntry.get().getLastName()),
                () -> assertEquals(true, optionalEmployeeDatabaseEntry.get().getFullTime())
                //() -> assertEquals(new BigDecimal(20000.00), optionalEmployeeDatabaseEntry.get().getSalary()),
                //() -> assertEquals(String.format("%.2f",new BigDecimal(25000.00)), String.format("%.2f", optionalEmployeeDatabaseEntry.get().getSalary()))
        );
    }
}