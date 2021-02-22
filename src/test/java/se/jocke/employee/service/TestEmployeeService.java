package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.department.entity.Employee;
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
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(100)
                .firstName("Fabian")
                .lastName("Ekbäck")
                .salary(BigDecimal.valueOf(76500.0))
                .fullTime(Boolean.TRUE)
                .departmentId(1)
                .build()));
    }
    @Test
    public void findById() {
        Employee employee = systemUnderTest.getEmployeeById(100);
        Assertions.assertAll(
                () -> Assertions.assertEquals(100, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("Fabian", employee.getFirstName()),
                () -> Assertions.assertEquals("Ekbäck", employee.getLastName()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(76500.0), employee.getSalary()),
                () -> Assertions.assertEquals(Boolean.TRUE, employee.getFullTime()),
                () -> Assertions.assertEquals(1, employee.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(100);
    }
}
