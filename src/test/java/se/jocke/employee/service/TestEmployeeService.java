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
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("Gunnar")
                .lastName("Svensson")
                .fullTime(true)
                .salary(BigDecimal.valueOf(25000))
                .departmentId(1)
                .build()));

    }
    @Test
    public void findById() {
        Employee employee = systemUnderTest.getEmployeeById(1);
        Assertions.assertAll(
                () -> assertEquals(1, employee.getEmployeeId().getId()),
                () -> assertEquals("Gunnar", employee.getFirstName()),
                () -> assertEquals("Svensson", employee.getLastName()),
                () -> assertEquals(BigDecimal.valueOf(25000), employee.getSalary())
        );
        System.out.println(employee);
        verify(employeeDao, times(1)).findById(1);
    }

    @Test
    public void remove() {
//        Employee employee = systemUnderTest.removeEmployee(EmployeeDatabaseEntry.builder()
//                .employeeId(1)
//                .firstName("Gunnar")
//                .lastName("Svensson")
//                .fullTime(true)
//                .salary(BigDecimal.valueOf(25000))
//                .departmentId(1)
//                .build()));

    }
}
