package se.jocke.employee.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.jocke.H2JpaConfig;
import se.jocke.LiquibaseConfigurer;
import se.jocke.department.entity.Employee;
import se.jocke.util.ObjectUtility;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
public class TestEmployeeDao {
    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void testGetEmployeeById() {
        ObjectUtility objectUtility = new ObjectUtility(); //Create list of all the Employee values.
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(1);
        List<Object> EmployeeEntryAttributes = objectUtility.ObjectUtility(optionalEmployeeDatabaseEntry.get());
       boolean result = EmployeeEntryAttributes.stream().anyMatch(e->e.equals(null));
        Assertions.assertFalse(result);  //Check if the call to employeeDao.findById() received an object
        //And then check if it has any null values, if not then test is passed.
    }
    @Test
    public void testGetAllEmployees() {
        List<EmployeeDatabaseEntry> employees = employeeDao.findAll();
        Assertions.assertAll(
                () -> assertNotNull(employees),
                () ->assertEquals(3,employees.size())
        );
    }
    @Test
    public void removeEmployee(Employee employee){

    }


}
