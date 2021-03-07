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
import se.jocke.department.entity.Employee;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
public class TestEmployeeDao {
    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void testGetEmployeeById(){
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(1);
        Assertions.assertAll(
                ()->assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                ()->assertNotNull(optionalEmployeeDatabaseEntry.get()),
                ()->assertEquals("firstName1", optionalEmployeeDatabaseEntry.get().getFirstName()),
                ()->assertEquals("lastName1", optionalEmployeeDatabaseEntry.get().getLastName())
        );
    }

    @Test
    public void testGetEmployees(){
        List<EmployeeDatabaseEntry> employees = employeeDao.findAll();
        Assertions.assertAll(
                ()->assertNotNull(employees),
                ()->assertEquals(3, employees.size())
        );
    }
}
