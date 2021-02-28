package se.jocke.employee.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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
@DisplayName("EMPLOYEE DAO TEST") // Titel som visas när vi testkör
public class TestEmployeeDao {
    @Autowired // ?
    EmployeeDao employeeDao; // JpaRepository

    @Test
    @DisplayName("GET EMPLOYEE BY ID TEST")
    public void testGetEmployeeById() {
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(4); // Varför Optional? För att vi kan hitta ingen

        assertTrue(optionalEmployeeDatabaseEntry.isPresent());
        assertNotNull(optionalEmployeeDatabaseEntry.get());
        assertEquals(4, optionalEmployeeDatabaseEntry.get().getEmployeeId());
        assertEquals("Mio", optionalEmployeeDatabaseEntry.get().getFirstName());
        assertEquals("Tholerus", optionalEmployeeDatabaseEntry.get().getLastName());
        assertEquals(new BigDecimal("27000"), optionalEmployeeDatabaseEntry.get().getSalary());
        assertEquals(false, optionalEmployeeDatabaseEntry.get().getFullTime());
        assertEquals(4, optionalEmployeeDatabaseEntry.get().getDepartmentId());
    }

    @Test
    @DisplayName("GET EMPLOYEES TEST")
    public void testGetEmployees() {
        List<EmployeeDatabaseEntry> employees = employeeDao.findAll();
        Assertions.assertAll(
                () -> assertNotNull(employees),
                () -> assertEquals(4,employees.size())
        );
    }

    @Test
    public void testSave() {
        // <S extends T> S save(S var1);
        EmployeeDatabaseEntry edbeSaved = employeeDao.save(
                new EmployeeDatabaseEntry().builder()
                        .employeeId(4)
                        .firstName("Carpe")
                        .lastName("Diem")
                        .salary(new BigDecimal(37000))
                        .fullTime(false)
                        .departmentId(4)
                        .build()
        );

        //assertions(edbeSaved);
        assertNotNull(edbeSaved);
        assertEquals(4, edbeSaved.getEmployeeId());
        assertEquals("Carpe", edbeSaved.getFirstName());
        assertEquals("Diem", edbeSaved.getLastName());
        assertEquals(new BigDecimal("37000"), edbeSaved.getSalary());
        assertEquals(false, edbeSaved.getFullTime());
        assertEquals(4, edbeSaved.getDepartmentId());
    }

    public void assertions(EmployeeDatabaseEntry edbe) {
        //Optional<EmployeeDatabaseEntry> edbe = employeeDao.findById(1); // Varför Optional? För att vi kan hitta ingen

        //assertTrue(edbe.isPresent());
//        assertNotNull(edbe);
//        assertEquals(4, edbe.getEmployeeId());
//        assertEquals("Mio", edbe.getFirstName());
//        assertEquals("Tholerus", edbe.getLastName());
//        assertEquals(new BigDecimal("27000").setScale(2), edbe.getSalary());
//        assertEquals(false, edbe.getFullTime());
//        assertEquals(4, edbe.getDepartmentId());
    }

    /**
     * Här KAN jag lägga in insert-, update- och delete-metoder för att öva på det,
     * men det fyller inget testfunktionellt syfte, pga JpaRepository är en redan testad produkt
     */
}
