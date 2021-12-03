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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
public class TestEmployeeDao {

    // gå igenom och förstå, lägg till mer?
    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void testGetEmployeeById() {
        Integer departmetId = 2; // ?
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(departmetId);
     /*   Assertions.assertAll(
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> assertNotNull(optionalEmployeeDatabaseEntry.get()),
              // fixa här  () -> assertEquals("?", optionalEmployeeDatabaseEntry.get().getEmployeeId()),
              //  () -> assertEquals(departmetId, optionalEmployeeDatabaseEntry.get().getDepartmentId())
        ); */
    }
}
