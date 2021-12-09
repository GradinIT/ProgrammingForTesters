package se.jocke.employee.unittest.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;

import se.jocke.config.LiquibaseConfigurer;
import se.jocke.config.PersistenceConfig;
import se.jocke.employee.dao.EmployeeDao;
import se.jocke.employee.dao.EmployeeDatabaseEntry;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

// @ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, PersistenceConfig.class})  // för employee-changelog

public class TestEmployeeDao {
    @Autowired  //klister

    EmployeeDao employeeDao;

    @Test
    public void testGetEmployeeById() {

        // gör att vi kan jämföra employee med id 1 i employee-changelog
        Integer employeeId = 1;
        // skapar lista med värden från employee-changelog employeeId 1 ---
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(employeeId);
        Assertions.assertAll(
                // kollar att det finns ett värde i optionalEmplyeeDatabaseEntry
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                // kollar att det inte är null i optanalEmplyeeDatabaseEntry
                () -> assertNotNull(optionalEmployeeDatabaseEntry.get()),
                // Kollar att Fagel är värdet i firstname för EmployeeId 1
                () -> assertEquals("Fagel", optionalEmployeeDatabaseEntry.get().getFirstName()),
                // jämför att employeeId är samma som det vi hämtar employee-changelog
                () -> assertEquals(employeeId,optionalEmployeeDatabaseEntry.get().getEmployeeId()),
                // Kollar att Holk är värdet i lastname för EmpyeeId 1
                () -> assertEquals("Holk",optionalEmployeeDatabaseEntry.get().getLastName()),
                // Kollar att fullTime är true för EmployeeId 1
                () -> assertEquals(true, optionalEmployeeDatabaseEntry.get().getFullTime()),

               /* () -> assertEquals(String.format("%.2f",new BigDecimal(25000.00)),String.format("%.2f", optionalEmployeeDatabaseEntry.get().getSalary())),

               // Gör int värdet 25000 till en BigDecimal 25000.00
       */ () -> assertEquals(new BigDecimal(25000.00), optionalEmployeeDatabaseEntry.get().getSalary().setScale(0, RoundingMode.HALF_UP))
              // hur funkar denna?  () -> assertEquals((double) 25000.00, optionalEmployeeDatabaseEntry.get().getSalary().setScale(1, RoundingMode.HALF_UP))
        );
    }
    @Test
    public void testEmployeeList (){
        List<EmployeeDatabaseEntry> employeeList = employeeDao.findAll();
        Assertions.assertAll(
                ()-> assertNotNull(employeeList), // kollar att listan inte är null
                () -> assertEquals(3, employeeList.size()) // förväntar oss 3 st employees i listan (i liquidbase changelog)
        );
    }
}