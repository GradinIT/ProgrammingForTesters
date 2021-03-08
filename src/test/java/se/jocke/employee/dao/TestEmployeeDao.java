package se.jocke.employee.dao;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
public class TestEmployeeDao {
    @Autowired
    EmployeeDao employeeDao;

    @Test
    // När jag kör testet enskilt så failar det, men kör jag hela klassen så lyckas det - lär bero på ordningen
    public void testGetEmployeeById() {
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(3);

        assertNotNull(optionalEmployeeDatabaseEntry.get());
        assertTrue(optionalEmployeeDatabaseEntry.isPresent());
        assertEquals(3, optionalEmployeeDatabaseEntry.get().getEmployeeId());
        assertEquals("firstName3", optionalEmployeeDatabaseEntry.get().getFirstName());
        assertEquals("lastName3", optionalEmployeeDatabaseEntry.get().getLastName());
        assertEquals(new BigDecimal("25000").setScale(2), optionalEmployeeDatabaseEntry.get().getSalary());
        assertEquals(true, optionalEmployeeDatabaseEntry.get().getFullTime());
        assertEquals(1, optionalEmployeeDatabaseEntry.get().getDepartmentId());
    }

    @Test
    // När jag kör testet enskilt så failar det, men kör jag hela klassen så lyckas det - lär bero på ordningen
    public void testGetEmployees() {
        List<EmployeeDatabaseEntry> employees = employeeDao.findAll();
        assertNotNull(employees);
        assertEquals(3,employees.size());
    }

//    @Test
//    // Körs först av någon anledning. OBS överflödigt test som dessutom gör de andra testen beroende :S
//    public void testSave() {
//        // <S extends T> S save(S var1);
//        EmployeeDatabaseEntry edbeSaved = employeeDao.save(
//                new EmployeeDatabaseEntry().builder()
//                        .employeeId(4)
//                        .firstName("Carpe")
//                        .lastName("Diem")
//                        .salary(new BigDecimal(37000))
//                        .fullTime(false)
//                        .departmentId(4)
//                        .build()
//        );
//        //assertions(edbeSaved);
//        assertNotNull(edbeSaved);
//        assertEquals(4, edbeSaved.getEmployeeId());
//        assertEquals("Carpe", edbeSaved.getFirstName());
//        assertEquals("Diem", edbeSaved.getLastName());
//        assertEquals(new BigDecimal("37000"), edbeSaved.getSalary());
//        assertEquals(false, edbeSaved.getFullTime());
//        assertEquals(4, edbeSaved.getDepartmentId());
//    }
}
