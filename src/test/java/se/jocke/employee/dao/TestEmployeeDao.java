package se.jocke.employee.dao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.jocke.H2JpaConfig;
import se.jocke.LiquibaseConfigurer;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;

import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.TRUE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
public class TestEmployeeDao {
    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void testGetEmployeeByID () {
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(1);
        Assertions.assertAll(
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> assertNotNull(optionalEmployeeDatabaseEntry.get()),
                () -> assertEquals(1, optionalEmployeeDatabaseEntry.get().getEmployeeId()),
                () -> assertEquals("firstName1", optionalEmployeeDatabaseEntry.get().getFirstName()),
                () -> assertEquals("lastName1", optionalEmployeeDatabaseEntry.get().getLastName()),
                () -> assertEquals(Boolean.TRUE, optionalEmployeeDatabaseEntry.get().getFullTime()),
                () -> assertEquals(new BigDecimal("25000.00"), optionalEmployeeDatabaseEntry.get().getSalary()),
                () -> assertEquals(1, optionalEmployeeDatabaseEntry.get().getDepartmentId())

        );
    }

    @Test
    public void testGetEmployees() {
        List<EmployeeDatabaseEntry> employees = employeeDao.findAll();
        Assertions.assertAll(
                () -> assertNotNull(employees),
                () -> assertEquals(3, employees.size())
        );
    }
}