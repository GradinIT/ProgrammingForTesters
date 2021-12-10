package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.employee.dao.EmployeeDatabaseEntry;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.dao.EmployeeDao;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {
    @Mock
    private EmployeeDao employeeDao;

    private Employee employee;

    @InjectMocks
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();

    @BeforeEach
    public void populateEmployeeObject() {
        employee = EmployeeTestBuilder.builder().build();
    }

    public EmployeeDatabaseEntry employeeDataBaseEntryBuilder(Employee employee) {
        return EmployeeDatabaseEntry.builder()
                .employeeId(employee.getEmployeeId().getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .salary(employee.getSalary())
                .fullTime(employee.getFullTime())
                .departmentId(employee.getDepartmentId())
                .build();
    }

    @Test
    @DisplayName("Test get employee by ID")
    public void getEmployeeById() {
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("Runar")
                .lastName("Runarsson")
                .salary(new BigDecimal(35000))
                .fullTime(true)
                .departmentId(1)
                .build()));

        Employee employee = systemUnderTest.getEmployeeById(1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("Runar", employee.getFirstName()),
                () -> Assertions.assertEquals("Runarsson", employee.getLastName()),
                () -> Assertions.assertEquals(new BigDecimal(35000), employee.getSalary()),
                () -> Assertions.assertEquals(true, employee.getFullTime()),
                () -> Assertions.assertEquals(1, employee.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(1);
    }

    @Test
    @DisplayName("Test get all employees")
    public void getAllEmployees() {
        // Tim
    }

    @Test
    @DisplayName("Test remove employee by ID")
    public void removeEmployeeById() {
        when(employeeDao.findById(employee.getEmployeeId().getId())).thenReturn(Optional.of(employeeDataBaseEntryBuilder(employee)));
        systemUnderTest.removeEmployee(employee);
        verify(employeeDao, times(1)).findById(employee.getEmployeeId().getId());
        verify(employeeDao, times(1)).delete(any(EmployeeDatabaseEntry.class));
    }

    @Test
    @DisplayName("Test update employee")
    public void updateEmployee() {
        // Oluyinka/Rasmus
        // båda gör en version - se om ni kommer till samma resultat
    }

    @Test
    @DisplayName("Test create employee Happy Flow")
    public void createEmployeeHappyFlow() {
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(employeeDataBaseEntryBuilder(employee));

        Employee createdEmployee = systemUnderTest.createEmployee(employee);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(createdEmployee),
                () -> Assertions.assertEquals(employee.getEmployeeId(), createdEmployee.getEmployeeId()),
                () -> Assertions.assertEquals(employee.getFirstName(), createdEmployee.getFirstName()),
                () -> Assertions.assertEquals(employee.getLastName(), createdEmployee.getLastName()),
                () -> Assertions.assertEquals(employee.getSalary(), createdEmployee.getSalary()),
                () -> Assertions.assertEquals(employee.getFullTime(), createdEmployee.getFullTime()),
                () -> Assertions.assertEquals(employee.getDepartmentId(), createdEmployee.getDepartmentId()));

        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    @DisplayName("Test create employee error")
    public void createEmployeeError() {
        // Ramin
    }
}