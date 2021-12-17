package se.jocke.employee.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.jocke.config.LiquibaseConfigurer;
import se.jocke.config.PersistenceConfig;
import se.jocke.department.dao.DepartmentDao;
import se.jocke.department.dao.DepartmentDatabaseEntry;
import se.jocke.employee.test.builder.EmployeeDatabaseEntryTestBuilder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, PersistenceConfig.class})
public class TestEmployeeDao {
    private final EmployeeDatabaseEntry ENTRY = EmployeeDatabaseEntryTestBuilder.bygg();

    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void testGetEmployeeById() {
        Integer employeeId = 1;
        Optional<EmployeeDatabaseEntry> optionalDepartmentDatabaseEntry = employeeDao.findById(employeeId);
        Assertions.assertAll(
                () -> assertTrue(optionalDepartmentDatabaseEntry.isPresent()),
                () -> assertNotNull(optionalDepartmentDatabaseEntry.get()),
                () -> assertEquals(employeeId, optionalDepartmentDatabaseEntry.get().getEmployeeId()),
                () -> assertEquals("Development", optionalDepartmentDatabaseEntry.get().getEmployeeId())
        );
    }

    @Test
    public void testGetEmployees(){

        List<EmployeeDatabaseEntry> employees = employeeDao.findAll();
        Assertions.assertAll(
                () -> assertNotNull(employees),
                () -> assertEquals(5, employees.size())
        );
    }

    //Update
    @Test
    public void testUpdateEmployee(){
        employeeDao.save(ENTRY);
        Assertions.assertAll(
                ()-> assertEquals(Boolean.TRUE, employeeDao.findById(ENTRY.getEmployeeId()).isPresent()),
                ()-> assertEquals(ENTRY, employeeDao.findById(ENTRY.getEmployeeId()).get())
        );
        EmployeeDatabaseEntry update = EmployeeDatabaseEntry.builder()
                .employeeId(ENTRY.getEmployeeId())
                .firstName("Klas")
                .lastName("Koding")
                .build();
        employeeDao.save(update);
        EmployeeDatabaseEntry updated = employeeDao.findById(ENTRY.getEmployeeId()).get();
        Assertions.assertAll(
                () -> assertEquals(Boolean.TRUE, employeeDao.findById(ENTRY.getEmployeeId()).isPresent()),
                ()-> assertEquals(update, updated)
        );
        employeeDao.delete(updated);
    }


    //delete
    @Test
    public void testDeleteEmployee(){
        employeeDao.save(ENTRY);
        Assertions.assertAll(
                ()-> assertEquals(Boolean.TRUE, employeeDao.findById(ENTRY.getEmployeeId()).isPresent()),
                ()-> assertEquals(ENTRY, employeeDao.findById(ENTRY.getEmployeeId()).get())
        );
        employeeDao.delete(ENTRY);
        Assertions.assertEquals(Boolean.TRUE, employeeDao.findById(ENTRY.getEmployeeId()).isEmpty());
    }

    //findAll
    @Test
    public void testFindAllEmployees(){
        List<EmployeeDatabaseEntry> employees = employeeDao.findAll();
        Assertions.assertAll(
                ()-> assertNotNull(employees),
                ()-> assertEquals(3, employees.size())
        );
    }
}
