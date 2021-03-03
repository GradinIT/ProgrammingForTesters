package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.stubbing.BaseStubbing;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.TestClient;
import se.jocke.api.EmployeeModel;
import se.jocke.dao.DepartmentDatabaseEntry;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.department.entity.Employee;
import se.jocke.department.entity.EmployeeID;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static se.jocke.TestClient.createEmployee;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {
    @Mock
    private EmployeeDao EmployeeDao;
    @InjectMocks
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        when(EmployeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("Linda")
                .lastName("Youkhanna")
                .salary(BigDecimal.valueOf(32000))
                .fullTime(true)
                .departmentId(1)
                .build()));

        when(EmployeeDao.findAll().size()).thenReturn(Optional.of(EmployeeDatabaseEntry.builder().build()));

    }
    @Test
    public void createOrUpdateEmployeeHappyFlow(){
        Employee employee = EmployeeTestBuilder.builder();

        when(EmployeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        when(EmployeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(EmployeeDatabaseEntry.builder()
                .employeeId(employee.getEmployeeId())
                .firstName(employee.getFirstName())
                .lastName(employee.getFirstName())
                .salary(employee.getSalary())
                .fullTime(employee.getFullTime())
                .departmentId(employee.getDepartmentId())
                .build());
        Employee createOrUpdateEmployee = systemUnderTest.createOrUpdateEmployee(employee);
        Assertions.assertAll(

                () -> assertNotNull(createOrUpdateEmployee),
                () -> assertEquals(employee.getEmployeeId(), createOrUpdateEmployee.getEmployeeId()),
                () -> assertEquals(employee.getFirstName(), createOrUpdateEmployee.getFirstName()),
                () -> assertEquals(employee.getLastName(), createOrUpdateEmployee.getLastName()),
                () -> assertEquals(employee.getSalary(), createOrUpdateEmployee.getSalary()),
                () -> assertEquals(employee.getFullTime(), createOrUpdateEmployee.getFullTime()),
                () -> assertEquals(employee.getDepartmentId(), createOrUpdateEmployee.getDepartmentId())
        );
        verify(EmployeeDao, times(1)).findById(any(Integer.class));
        verify(EmployeeDao, times(1)).save(EmployeeDatabaseEntry.builder().build());


    }

    @Test
    public void findById() {

        Employee employee = systemUnderTest.getEmployeeById(1);
        Assertions.assertAll(
                () -> assertEquals(1,employee.getEmployeeId().getId()),
                () -> assertEquals("Linda",employee.getFirstName()),
                () -> assertEquals("Youkhanna",employee.getLastName()),
                () -> assertEquals(BigDecimal.valueOf(32000),employee.getSalary()),
                () -> assertEquals(true,employee.getFullTime()),
                () -> assertEquals(1,employee.getDepartmentId())

        );
        verify(EmployeeDao, times(1)).findById(1);


    }
    @Test
    public void updateEmployee(){
        Employee employee = systemUnderTest.getEmployeeById(1);
        Assertions.assertAll(
                () -> assertEquals(1,employee.getEmployeeId().getId()),
                () -> assertEquals("Linda",employee.getFirstName()),
                () -> assertEquals("Youkhanna",employee.getLastName()),
                () -> assertEquals(BigDecimal.valueOf(32000),employee.getSalary()),
                () -> assertEquals(true,employee.getFullTime()),
                () -> assertEquals(1,employee.getDepartmentId())
        );
        verify(EmployeeDao, times(1)).findById(1);

    }
    @Test
    public void removeEmployee(){
        Employee employee = systemUnderTest.getEmployeeById(1);
        Assertions.assertAll(
                () -> assertEquals(1,employee.getEmployeeId().getId()),
                () -> assertEquals("Linda",employee.getFirstName()),
                () -> assertEquals("Youkhanna",employee.getLastName()),
                () -> assertEquals(BigDecimal.valueOf(32000),employee.getSalary()),
                () -> assertEquals(true,employee.getFullTime()),
                () -> assertEquals(1,employee.getDepartmentId())
        );
        verify(EmployeeDao, times(1)).findById(1);
    }
    /*@Test
    public void getAllEmployees() {
        List<EmployeeModel> numberOfEmployees = TestClient.getAllEmployees();
        Assertions.assertEquals(numberOfEmployees, employees.getAll().size());
        verify(EmployeeDao, times(1));
    }*/
}
