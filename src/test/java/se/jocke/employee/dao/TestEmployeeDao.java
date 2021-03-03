package se.jocke.employee.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.jocke.H2JpaConfig;
import se.jocke.LiquibaseConfigurer;
import se.jocke.dao.DepartmentDao;
import se.jocke.dao.DepartmentDatabaseEntry;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
public class TestEmployeeDao {
    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void testGetEmployeeById() {
        Optional<EmployeeDatabaseEntry> optionalEmployeeEntry = employeeDao.findById(1);
        Assertions.assertAll(
                () -> assertTrue(optionalEmployeeEntry.isPresent()),
                () -> assertNotNull(optionalEmployeeEntry.get()),
                () -> assertEquals(1,optionalEmployeeEntry.get().getEmployeeId()),
                () -> assertEquals("firstName1", optionalEmployeeEntry.get().getFirstName()),
                () -> assertEquals("lastName1", optionalEmployeeEntry.get().getLastName()),
                () -> assertEquals(true, optionalEmployeeEntry.get().getFullTime()),
                () -> assertEquals(BigDecimal.valueOf(25000).setScale(2),optionalEmployeeEntry.get().getSalary()),
                () -> assertEquals(1, optionalEmployeeEntry.get().getDepartmentId())
        );
    }
    @Test
    public void testGetEmployee() {
        List<EmployeeDatabaseEntry> employees = employeeDao.findAll();
        Assertions.assertAll(
                () -> assertNotNull(employees),
                () ->assertEquals(3,employees.size())
        );
    }
}


