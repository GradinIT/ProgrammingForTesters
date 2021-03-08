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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
public class TestEmployeeDao { // No Mocking done yet, 2/3-21 Added all parameters, methods corrected, tests passed with all default values,
    @Autowired
    EmployeeDao employeeDao;

    @Test   //Test passed 28/2-21 when only 2 parameters. Row 49: typo of assertNull, corrected to assertNotNull,
    public void testGetEmployeeById() {
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(1); // return value should be Optional if the requested object is of Optional type.
        Assertions.assertAll(
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> assertNotNull(optionalEmployeeDatabaseEntry.get()),
                () -> assertEquals("Lotta",optionalEmployeeDatabaseEntry.get().getFirstName()),
                () -> assertEquals("lastName1",optionalEmployeeDatabaseEntry.get().getLastName()),
                () -> assertEquals(25000.00,optionalEmployeeDatabaseEntry.get().getSalary().longValue()),
                () -> assertEquals(true,optionalEmployeeDatabaseEntry.get().getFullTime()),
                () -> assertEquals(1,optionalEmployeeDatabaseEntry.get().getDepartmentId())
        );
    }

    @Test
    public void testGetEmployees() { // Test passed. By default 3 employees. If more employees are added, one
//                                   might need to adjust departmentTest.feature-file in resources?
        List<EmployeeDatabaseEntry> employees = employeeDao.findAll();
        Assertions.assertAll(
                () -> assertNotNull(employees),
                () -> assertEquals(4,employees.size())
        );
    }
}
