package se.jocke.employee.service;

/* MALL
* @ExtendWith(MockitoExtension.class)
public class TestDepartmentService {
    @Mock
    private DepartmentDao departmentDao;
    @InjectMocks
    private DepartmentService systemUnderTest = new DepartmentServiceImpl();
*/

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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Hela klassen befinner sig i en Mockitokontext, inte SpringBoot
public class TestEmployeeService { // Bygg ut med alla "CRUD"-kommandon, finn dem under EmployeeService
    @Mock
    private EmployeeDao employeeDao;
    @InjectMocks // EmployeeServiceImpl är beroende av interface EmployeeDao för att fungera. Därför injiceras mocken/imitationen av employeeDao i EmployeeServiceImpl. Om det inte
    private EmployeeService systemUnderTest = new EmployeeServiceImpl(); //är Mockitokontext så sköter SpringBoot injectionerna åt oss (förutsatt annotetringar)

    /* MALL
    *  @BeforeEach
    public void setUp() {
        when(departmentDao.findById(any(Integer.class))).thenReturn(Optional.of(DepartmentDatabaseEntry.builder()
                .departmentId(1)
                .departmentName("Development")
                .build()));
    }*/

    @BeforeEach                               //My try of method for EmployeeTesting
    public void setUp() {
        when(employeeDao.findById(any((Integer.class)))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(4)
                .firstName("Grodan")
                .lastName("Boll")
                .fullTime(false)
                .salary(BigDecimal.valueOf(45000.00)) // field salary of EmployeeDatabaseEntry is of BigDecimal-type
                .departmentId(2)
                .build()));
    }

    /* MALL
    *  @Test
    public void findById() {
        Department department = systemUnderTest.getDepartmentById(1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, department.getDepartmentId()),
                () -> Assertions.assertEquals("Development", department.getDepartmentName())
        );
        verify(departmentDao, times(1)).findById(1);
    }*/

    @Test // My own test to understand where the EntityID comes from.
    public void test(){
        Employee employee = systemUnderTest.getEmployeeById(1);
        System.out.println(employee.getEmployeeId());
    }

    /* MALL
    Employee getEmployeeById(Integer employeeId);
    Employee createOrUpdateEmployee(Employee employee);
    Employee removeEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    List<Employee> getAllEmployees(); */

    @Test
    public void findById() {
        Employee employee = systemUnderTest.getEmployeeById(1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(4, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("Grodan",employee.getFirstName()),
                () -> Assertions.assertEquals("Boll",employee.getLastName()),
                () -> Assertions.assertEquals(false,employee.getFullTime()),
                () -> Assertions.assertEquals(45000.00,employee.getSalary().longValue()),
                () -> Assertions.assertEquals(2,employee.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(1);
    }



}
