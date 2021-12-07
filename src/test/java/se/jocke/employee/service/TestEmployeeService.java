package se.jocke.employee.service;

import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.employee.dao.EmployeeDao;
import se.jocke.employee.dao.EmployeeDatabaseEntry;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import se.jocke.employee.entity.Employee;

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

    //@Test
    public void deleteEmployeeById() {
      when(employeeDao.findAll()).thenReturn(Arrays.asList(EmployeeDatabaseEntry.builder()

                .employeeId(2)
                .firstName("Glen")
                .lastName("Svensson")
                .salary(new BigDecimal(40000))
                .fullTime(true)
                .departmentId(2)
                .build()));

        List<Employee> employees = systemUnderTest.getAllEmployees(); // Skapar en lista med alla anställda
        Assertions.assertEquals(1, employees.size());         // Kontrollerar att antalet i listan är förväntat
        systemUnderTest.removeEmployee(employees.get(0));             // Försöker ta bort anställd
        List<Employee> deletedEmployee = systemUnderTest.getAllEmployees(); // skapar ny lista med anställda
        Assertions.assertEquals(0, deletedEmployee.size());         // kontrollerar att antalet i listan är förväntat
    }

    @Test
    public void getAllEmployees() {
    }

    @Test
    public void existById() {
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