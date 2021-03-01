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
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {

    private Employee testEmp;
    private Employee tempEmp;
    private EmployeeDatabaseEntry empDbE;

    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private final EmployeeService SYSTEM_UNDER_TEST = new EmployeeServiceImpl();

    @BeforeEach
    void setUp() {
        tempEmp = null;
        testEmp = EmployeeTestBuilder.builder().build();
        empDbE = EmployeeDatabaseEntry.builder()
                .employeeId(testEmp.getEmployeeId().getId())
                .firstName(testEmp.getFirstName())
                .lastName(testEmp.getLastName())
                .salary(testEmp.getSalary())
                .fullTime(testEmp.getFullTime())
                .departmentId(testEmp.getDepartmentId())
                .build();
    }

    @Test
    public void testFindEmployee() {
        when(employeeDao.findById(anyInt())).thenReturn(Optional.of(empDbE));

        tempEmp = SYSTEM_UNDER_TEST.getEmployeeById(testEmp.getEmployeeId().getId());
        Assertions.assertAll(
                () -> assertEquals(testEmp.getEmployeeId().getId(), tempEmp.getEmployeeId().getId()),
                () -> assertEquals(testEmp.getFirstName(), tempEmp.getFirstName()),
                () -> assertEquals(testEmp.getLastName(), tempEmp.getLastName()),
                () -> assertEquals(testEmp.getSalary(), tempEmp.getSalary()),
                () -> assertEquals(testEmp.getFullTime(), tempEmp.getFullTime()),
                () -> assertEquals(testEmp.getDepartmentId(), tempEmp.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(testEmp.getEmployeeId().getId());
    }

    @Test
    public void testCreateEmployee() {
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(empDbE);

        tempEmp = SYSTEM_UNDER_TEST.createOrUpdateEmployee(testEmp);
        Assertions.assertAll(
                () -> assertEquals(testEmp.getEmployeeId().getId(), tempEmp.getEmployeeId().getId()),
                () -> assertEquals(testEmp.getFirstName(), tempEmp.getFirstName()),
                () -> assertEquals(testEmp.getLastName(), tempEmp.getLastName()),
                () -> assertEquals(testEmp.getSalary(), tempEmp.getSalary()),
                () -> assertEquals(testEmp.getFullTime(), tempEmp.getFullTime()),
                () -> assertEquals(testEmp.getDepartmentId(), tempEmp.getDepartmentId())
        );
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }
}
