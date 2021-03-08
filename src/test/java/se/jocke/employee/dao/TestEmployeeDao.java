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


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
public class TestEmployeeDao {

    @Autowired
    // interface
    EmployeeDao employeeDao;

    // Testar om vi får ut det vi vill från databasen på id(1)
    @Test
    public void testGetEmployeeById() {
        // Optional objektet använder sig ofta utav metoden isPresent() som kollar om Optional
        // instansen innehåller något värde.
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(1);
        Assertions.assertAll(
                // kollar om vi får något värde
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                // returnerar värdet om det finns
                () -> assertNotNull(optionalEmployeeDatabaseEntry.get()),
                // ser om värdet är det vi vill att det ska vara
                () -> assertEquals("firstName1",optionalEmployeeDatabaseEntry.get().getFirstName())
        );
    }
    @Test
    public void testGetEmployees() {
        // skapar en lista av databasens innehåll
        List<EmployeeDatabaseEntry> employees = employeeDao.findAll();
        for(EmployeeDatabaseEntry emp : employees){
            System.out.println("--------" + emp);
        }
        Assertions.assertAll(
                // kollar så att listan inte är null
                () -> assertNotNull(employees),
                // kollar att listan innehåller 3 tre objekt
                () ->assertEquals(3,employees.size())
        );
    }
}

