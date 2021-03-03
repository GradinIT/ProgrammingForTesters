package se.jocke.employee.dao;

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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
public class TestEmployeeDao {      //Här gör vi JUnit tester mot den lokala ram-databasen genom liquid base.
    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void testGetEmployeeById(){  //Denna test kikar i h2 databasen efter inserts
        Integer employeeId = 1;
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(employeeId);
        Assertions.assertAll(
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> assertNotNull(optionalEmployeeDatabaseEntry.get()),
                () -> assertEquals(employeeId, optionalEmployeeDatabaseEntry.get().getEmployeeId()),
                () -> assertEquals("firstName1", optionalEmployeeDatabaseEntry.get().getFirstName()),
                () -> assertEquals("lastName1", optionalEmployeeDatabaseEntry.get().getLastName()),
                () -> assertEquals(Boolean.TRUE, optionalEmployeeDatabaseEntry.get().getFullTime()),
                () -> assertEquals (25000.0, optionalEmployeeDatabaseEntry.get().getSalary()),
                () -> assertEquals(1, optionalEmployeeDatabaseEntry.get().getDepartmentId())
        );
    }

    @Test
    public void testGetEmployee(){
        List<EmployeeDatabaseEntry> employees = employeeDao.findAll();
        Assertions.assertAll(
                ()-> assertNotNull(employees),
                ()-> assertEquals(3, employees.size())
        );
    }
}