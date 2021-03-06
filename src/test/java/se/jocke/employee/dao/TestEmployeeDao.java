package se.jocke.employee.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.jocke.H2JpaConfig;
import se.jocke.LiquibaseConfigurer;
import se.jocke.dao.DepartmentDao;
import se.jocke.dao.DepartmentDatabaseEntry;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {LiquibaseConfigurer.class, H2JpaConfig.class})
public class TestEmployeeDao {
    @Autowired
    EmployeeDao employeeDao;

    private EmployeeDatabaseEntry tester = new EmployeeDatabaseEntry();

    @BeforeEach
    public void setUp() {
        tester = EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("firstName1")
                .lastName("lastName1")
                .salary(BigDecimal.valueOf(25000))
                .fullTime(true)
                .departmentId(1)
                .build();
    }

    @DisplayName("Comparing database entry with tester set up after what should be in database for ID 1")
    @Test
    public void testGetEmployeeById() {
        Optional<EmployeeDatabaseEntry> optionalEmployeeDatabaseEntry = employeeDao.findById(1);

        Assertions.assertAll(
                () -> assertTrue(optionalEmployeeDatabaseEntry.isPresent()),
                () -> assertNotNull(optionalEmployeeDatabaseEntry.get()),
                () -> assertEquals(tester.getEmployeeId(),optionalEmployeeDatabaseEntry.get().getEmployeeId()),
                () -> assertEquals(tester.getFirstName(), optionalEmployeeDatabaseEntry.get().getFirstName()),
                () -> assertEquals(tester.getLastName(), optionalEmployeeDatabaseEntry.get().getLastName()),
                () -> assertEquals(tester.getSalary(), optionalEmployeeDatabaseEntry.get().getSalary()),
                () -> assertEquals(tester.getFullTime(), optionalEmployeeDatabaseEntry.get().getFullTime()),
                () -> assertEquals(tester.getDepartmentId(), optionalEmployeeDatabaseEntry.get().getDepartmentId())
        );
    }

    @DisplayName("Testing number of employees in database, should return 3")
    @Test
    public void testGetEmployees() {
        List<EmployeeDatabaseEntry> employees = employeeDao.findAll();
        Assertions.assertAll(
                () -> assertNotNull(employees),
                () ->assertEquals(3,employees.size())
        );
    }
}
