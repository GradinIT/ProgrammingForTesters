package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.api.EmployeeModel;
import se.jocke.dao.DepartmentDatabaseEntry;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.department.entity.Department;
import se.jocke.department.entity.Employee;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
                .firstName("firstName1")
                .lastName("lastName1")
                .fullTime(Boolean.TRUE)
                .salary(BigDecimal.valueOf(25000.00))
                .departmentId(1)
                .build()));
    }

    @Test
    public void findById() {
        Employee employee = systemUnderTest.getEmployeeById(1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("firstName1", employee.getFirstName()),
                () -> Assertions.assertEquals("lastName1", employee.getLastName()),
                () -> Assertions.assertEquals(Boolean.TRUE, employee.getFullTime()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(25000.00), employee.getSalary()),
                () -> Assertions.assertEquals(1, employee.getDepartmentId())
    );
        verify(employeeDao, times(1)).findById(1);
    }

    @Test
    public void createEmployee() {
        //1 Skapa en ny employee med employeeID 77
        //2 Kalla på metoden createEmployee-metoden i TestClient
        //3 Skriv assertions
        }

    @Test
    public void updateEmployee() {
        //1 hitta en employee
        //2 uppdatera mha updateEmployee-metoden i TestClient
    }

    @Test
    public void removeEmployee() {
        //1 hitta en employee
        Employee employee = systemUnderTest.getEmployeeById(1);
        //använder removeEmployee från EmployeeServiceImpl
        systemUnderTest.removeEmployee(employee);
        verify(employeeDao,times(1)).delete(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void listAllEmployees() {
        Employee employee = systemUnderTest.getEmployeeById(1);
        List<Employee> employees = systemUnderTest.getAllEmployees();
        employees.add(employee);
        employees.add(employee);
        employees.add(employee);
        verify(employeeDao, times(1)).findAll();
    }
}
//1 Lista alla employees mha getAllEmployees-metoden i EmployeeServiceImpl
// systemUnderTest.getAllEmployees();
//2 skriv assertions?