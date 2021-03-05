package se.jocke.employee.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
public class TestEmployeeDao {
    @Autowired
    EmployeeDao employeeDao;

    //Här kan man egentligen välja att använda antingen assertTrue och assertNotNull
    @Test
    public void testGetEmployeeById() {
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(1);
        Assertions.assertAll(
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()), //kollar att raden finns
                () -> assertNotNull(optionalEmployeeDatabaseEntry.get()), //kollar att inget är null
                () -> assertEquals(1,optionalEmployeeDatabaseEntry.get().getEmployeeId()),
                () -> assertEquals("firstName1",optionalEmployeeDatabaseEntry.get().getFirstName()),
                () -> assertEquals("lastName1",optionalEmployeeDatabaseEntry.get().getLastName()),
                () -> assertEquals(true,optionalEmployeeDatabaseEntry.get().getFullTime()),
                () -> assertEquals(BigDecimal.valueOf(25000).longValue(),optionalEmployeeDatabaseEntry.get().getSalary().longValue()),
                () -> assertEquals(1,optionalEmployeeDatabaseEntry.get().getDepartmentId())
        );
    }




    //Null-värdet har med hela listan att göra
    @Test
    public void testGetEmployees() {
        List<EmployeeDatabaseEntry> employees = employeeDao.findAll();
        Assertions.assertAll(
                () -> assertNotNull(employees),
                () -> assertEquals(3,employees.size())
        );
    }
}
