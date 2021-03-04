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
import static org.junit.Assert.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
public class TestEmployeeDao {

    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void testGetEmployees(){

        List<EmployeeDatabaseEntry> employees = employeeDao.findAll();
        Assertions.assertAll(

                () -> assertNotNull(employees),
                () -> assertEquals(3,employees.size())
        );
    }

    @Test
    public void testGetEmployeeById(){

        Integer employeeId = 1;
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(employeeId);

        Assertions.assertAll(

                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> assertNotNull(optionalEmployeeDatabaseEntry.get()),
                () -> assertEquals(employeeId ,optionalEmployeeDatabaseEntry.get().getEmployeeId()),
                () -> assertEquals(1 ,(int)optionalEmployeeDatabaseEntry.get().getDepartmentId()),
                () -> assertEquals("firstName1", optionalEmployeeDatabaseEntry.get().getFirstName()),
                () -> assertEquals("lastName1", optionalEmployeeDatabaseEntry.get().getLastName()),
                () -> assertTrue(optionalEmployeeDatabaseEntry.get().getFullTime()),
                () -> assertEquals((long)25000.00, optionalEmployeeDatabaseEntry.get().getSalary().longValue())

        );
    }

}
