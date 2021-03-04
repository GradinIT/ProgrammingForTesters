package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.dao.EntityNotFoundException;
import se.jocke.dao.mapper.EmployeePojoMapper;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {
    @Mock // Varför
    private EmployeeDao employeeDao;
    @InjectMocks
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();

    private Employee exampleEmployee = EmployeeTestBuilder.builderMethod().build();

    // Utöka med att testa de andra metoderna i EmployeeServiceImpl?
    @Test
    public void testGetEmployeeById() {
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("Mock")
                .lastName("Mockesson")
                .salary(BigDecimal.valueOf(22000))
                .fullTime(true)
                .departmentId(5)
                .build()));

        Employee employee = systemUnderTest.getEmployeeById(1);

        Assertions.assertEquals(1, employee.getEmployeeId().getId());
        Assertions.assertEquals("Mock", employee.getFirstName());

        verify(employeeDao, times(1)).findById(1); // Vad gör verify? Varför vill vi veta hur många gånger findById körs?
    }

    @Test
    public void testCreateEmployee() {
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(EmployeeDatabaseEntry.builder()
                .employeeId(exampleEmployee.getEmployeeId().getId())
                .firstName(exampleEmployee.getFirstName())
                .lastName(exampleEmployee.getLastName())
                .salary(exampleEmployee.getSalary())
                .fullTime(exampleEmployee.getFullTime())
                .departmentId(exampleEmployee.getDepartmentId())
                .build());

        Employee addedEmployee = systemUnderTest.createEmployee(exampleEmployee);

        Assertions.assertNotNull(addedEmployee);
        Assertions.assertEquals(exampleEmployee.getEmployeeId().getId(), addedEmployee.getEmployeeId().getId());
        Assertions.assertEquals(exampleEmployee.getFirstName(), addedEmployee.getFirstName());
        Assertions.assertEquals(exampleEmployee.getLastName(), addedEmployee.getLastName());
        Assertions.assertEquals(exampleEmployee.getSalary(), addedEmployee.getSalary());
        Assertions.assertEquals(exampleEmployee.getFullTime(), addedEmployee.getFullTime());
        Assertions.assertEquals(exampleEmployee.getDepartmentId(), addedEmployee.getDepartmentId());
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void testRemoveEmployee() {
        when(employeeDao.findById(exampleEmployee.getEmployeeId().getId()))
                .thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                        .employeeId(exampleEmployee.getEmployeeId().getId())
                        .firstName(exampleEmployee.getFirstName())
                        .lastName(exampleEmployee.getLastName())
                        .salary(exampleEmployee.getSalary())
                        .fullTime(exampleEmployee.getFullTime())
                        .departmentId(exampleEmployee.getDepartmentId())
                        .build()));

        systemUnderTest.removeEmployee(exampleEmployee);

        verify(employeeDao, times(1)).delete(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void testRemoveEmployeeErrorMessage() {
        when(employeeDao.findById(exampleEmployee.getEmployeeId().getId()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> systemUnderTest.removeEmployee(exampleEmployee));

        verify(employeeDao,times(1)).findById(any());
        verify(employeeDao,times(0)).delete(any());
    }

    @Test
    public void testUpdateEmployee() {
        when(employeeDao.findById(exampleEmployee.getEmployeeId().getId())).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(exampleEmployee.getEmployeeId().getId())
                .firstName(exampleEmployee.getFirstName())
                .lastName(exampleEmployee.getLastName())
                .salary(exampleEmployee.getSalary())
                .fullTime(exampleEmployee.getFullTime())
                .departmentId(exampleEmployee.getDepartmentId())
                .build()));

        systemUnderTest.updateEmployee(exampleEmployee);
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void testGetAllEmployee() {

    }

    public Employee getExampleEmployee() {
        return exampleEmployee = EmployeeTestBuilder
                .builderMethod()
                .build();
    }
}
