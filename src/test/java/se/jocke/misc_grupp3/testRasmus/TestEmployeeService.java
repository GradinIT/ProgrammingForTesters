package se.jocke.misc_grupp3.testRasmus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.employee.dao.EmployeeDao;
import se.jocke.employee.dao.EmployeeDatabaseEntry;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.service.EmployeeService;
import se.jocke.employee.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {
    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void getEmployeeById() {
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("Runar")
                .lastName("Runarsson")
                .salary(new BigDecimal(35000))
                .fullTime(true)
                .departmentId(1)
                .build()));
        // comment

        Employee employee = systemUnderTest.getEmployeeById(1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("Runar", employee.getFirstName()),
                () -> Assertions.assertEquals("Runarsson", employee.getLastName()),
                () -> Assertions.assertEquals(new BigDecimal(35000), employee.getSalary()),
                () -> Assertions.assertTrue(true),
                () -> Assertions.assertEquals(1, employee.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(1);
    }

    @Test
    public void getAllEmployees() {
    }

    @Test
    public void existById() {
    }

    @Test
    public void deleteEmployeeById() {
    }

    @Test
    public void countAllEmployees() {
    }

    @Test
    public void createEmployeeHappyFlow() {
    }

    @Test
    public void createEmployeeError() {
    }
}