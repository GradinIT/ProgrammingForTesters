package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.department.entity.Employee;
import se.jocke.department.entity.EmployeeID;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {
    @Mock
    private EmployeeDao employeeDao;
    @InjectMocks
    private EmployeeService systemUnderTest = new EmployeeServiceImpl(); // Vad är det?

    @BeforeEach
    public void setUp() { // Setup inför varje test
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(4) // Funkar en entitet jag inte har lagt in i db ännu?
                .firstName("Mio")
                .lastName("Tholerus")
                .salary(BigDecimal.valueOf(25000.0)) // ?
                .fullTime(false) // Boolean.FALSE?
                .departmentId(4)
                .build()));
    }

    @Test
    public void findById() {
        Employee employee = systemUnderTest.getEmployeeById(4);

        Assertions.assertEquals(4, employee.getEmployeeId().getId()); // blir det ett korrekt test med .getId()?
        Assertions.assertEquals("Mio", employee.getFirstName());
        Assertions.assertEquals("Tholerus", employee.getLastName());
        Assertions.assertEquals(BigDecimal.valueOf(25000.0), employee.getSalary());
        Assertions.assertEquals(Boolean.FALSE, employee.getFullTime());
        Assertions.assertEquals(4, employee.getDepartmentId());

        verify(employeeDao, times(1)).findById(4);
    }
}
