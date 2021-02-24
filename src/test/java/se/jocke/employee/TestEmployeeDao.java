package se.jocke.employee;

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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/* MALL
 @ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
public class TestDepartmentDao {
   @Autowired
   DepartmentDao departmentDao; */

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
public class TestEmployeeDao {
    @Autowired
    EmployeeDao employeeDao;

/* MALL
@Test
    public void testGetDepartmentById() {
        Optional<DepartmentDatabaseEntry> optionalEmployeeDatabaseEntry = departmentDao.findById(1);
        Assertions.assertAll(
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> assertNotNull(optionalEmployeeDatabaseEntry.get()),
                () -> assertEquals("Development",optionalEmployeeDatabaseEntry.get().getDepartmentName())
        );
    } */

    @Test
    public void testGetEmployeeById() { // test failed. Expected:null, Actual:EmployeeDatabaseEntry(employeeId=1, firstName=firstName1, lastName=lastName1, salary=25000.00, fullTime=true, departmentId=1)

        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(1);
        Assertions.assertAll(
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> assertNull(optionalEmployeeDatabaseEntry.get()),
                () -> assertEquals("Development",optionalEmployeeDatabaseEntry.get().getFirstName())
        );
    }


}
