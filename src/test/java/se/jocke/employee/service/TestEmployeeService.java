package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.dao.EntityAlreadyInStorageException;
import se.jocke.dao.EntityNotFoundException;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//Enhetstest - testar inte hela flödet
//Sätter upp miljön för mockning, så vi köra testet
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TestEmployeeService {

    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private final EmployeeService systemUndertest = new EmployeeServiceImpl();

    @BeforeEach
    public void setUp() {

    }

    //Testar getEmployeeById(Integer employee)
    @Test
    public void getEmployeeById() {
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .departmentId(2)
                .firstName("firstName1")
                .lastName("lastName1")
                .fullTime(true)
                .salary(BigDecimal.valueOf(25000.00))
                .build()));

        Employee employee = systemUndertest.getEmployeeById(1);
        Assertions.assertAll(
                () -> assertEquals(1, employee.getEmployeeId().getId()),
                () -> assertEquals("firstName1", employee.getFirstName()),
                () -> assertEquals("lastName1", employee.getLastName()),
                () -> assertEquals(2, employee.getDepartmentId()),
                () -> assertEquals(true, employee.getFullTime()),
                () -> assertEquals(BigDecimal.valueOf(25000.00), employee.getSalary()));
        verify(employeeDao, times(1)).findById(1);
    }

    @Test
    public void getAllEmployees() {
        when(employeeDao.findAll()).thenReturn(Arrays.asList(EmployeeDatabaseEntry.builder()
                .firstName("firstName1")
                .lastName("lastName1")
                .fullTime(true)
                .departmentId(2)
                .salary(BigDecimal.valueOf(25000.00))
                .employeeId(1)
                .build()));

        List<Employee> employees = systemUndertest.getAllEmployees();
        Assertions.assertAll(
                () -> assertNotNull(employees),
                () -> assertEquals(1, employees.size())
        );

    }

    @Test
    public void createEmployeeHappyFlow() {
        Employee employee = EmployeeTestBuilder.builder().build();
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(EmployeeDatabaseEntry.builder()
                .employeeId(employee.getEmployeeId().getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .fullTime(employee.getFullTime())
                .salary(employee.getSalary())
                .departmentId(employee.getDepartmentId())
                .build());

        Employee createEmployee = systemUndertest.createEmployee(employee);
        Assertions.assertAll(
                () -> assertNotNull(createEmployee),
                () -> assertEquals(employee.getEmployeeId().getId(), createEmployee.getEmployeeId().getId()),
                () -> assertEquals(employee.getLastName(), createEmployee.getLastName()),
                () -> assertEquals(employee.getFirstName(), createEmployee.getFirstName()),
                () -> assertEquals(employee.getDepartmentId(), createEmployee.getDepartmentId()),
                () -> assertEquals(employee.getFullTime(), createEmployee.getFullTime()),
                () -> assertEquals(employee.getSalary(), createEmployee.getSalary()));
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
        verify(employeeDao, times(1)).findById(any(Integer.class));

    }

    @Test
    public void createEmployeeError() {
        Employee employee = EmployeeTestBuilder.builder().build();
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(employee.getEmployeeId().getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .fullTime(employee.getFullTime())
                .salary(employee.getSalary())
                .departmentId(employee.getDepartmentId())
                .build()));
        Assertions.assertThrows(EntityAlreadyInStorageException.class, () -> {
            systemUndertest.createEmployee(employee);
        });
        verify(employeeDao, times(1)).findById(any(Integer.class));

    }

    //Förväntar mig att det finns ett id - testa det som finns
    //Testar updateEmployee(Employee employee), normalflödet (Happy flow)
    @Test
    public void updateEmployeeHappyFlow() {
        Employee employee = EmployeeTestBuilder.builder().build();
        EmployeeDatabaseEntry updateEmployee = EmployeeDatabaseEntry.builder()
                .employeeId(employee.getEmployeeId().getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .fullTime(employee.getFullTime())
                .salary(employee.getSalary())
                .departmentId(employee.getDepartmentId())
                .build();

        lenient().when(employeeDao.save(any())).thenReturn(updateEmployee);

        Assertions.assertAll(
                () -> assertNotNull(updateEmployee),
                () -> assertEquals(employee.getEmployeeId().getId(), updateEmployee.getEmployeeId()),
                () -> assertEquals(employee.getLastName(), updateEmployee.getLastName()),
                () -> assertEquals(employee.getFirstName(), updateEmployee.getFirstName()),
                () -> assertEquals(employee.getDepartmentId(), updateEmployee.getDepartmentId()),
                () -> assertEquals(employee.getFullTime(), updateEmployee.getFullTime()),
                () -> assertEquals(employee.getSalary(), updateEmployee.getSalary()));
    }

    //Förväntar mig att det inte finns ett id - skickar ett exception
    @Test
    public void updateEmployeeError() {
        Employee employee = EmployeeTestBuilder.builder().build();
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(employee.getEmployeeId().getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .fullTime(employee.getFullTime())
                .salary(employee.getSalary())
                .departmentId(employee.getDepartmentId())
                .build()));
        Assertions.assertThrows(NullPointerException.class, () -> {
            systemUndertest.updateEmployee(employee);
        });
        verify(employeeDao, times(1)).findById(any(Integer.class));
    }

    //Testar removeEmployee(Employee employee)
    //Förväntar mig att det finns ett id - testar att ta bort
    //mocka findbyid, anropar delete - retunera värdet från employee
    @Test
    public void removeEmployee() {
        Employee employee = EmployeeTestBuilder.builder().build();
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(employee.getEmployeeId().getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .fullTime(employee.getFullTime())
                .salary(employee.getSalary())
                .departmentId(employee.getDepartmentId())
                .build()));
        Employee removeEmployee = systemUndertest.removeEmployee(employee);

        Assertions.assertAll(
                () -> assertNotNull(removeEmployee),
                () -> assertEquals(employee.getEmployeeId().getId(), removeEmployee.getEmployeeId().getId()),
                () -> assertEquals(employee.getLastName(), removeEmployee.getLastName()),
                () -> assertEquals(employee.getFirstName(), removeEmployee.getFirstName()),
                () -> assertEquals(employee.getDepartmentId(), removeEmployee.getDepartmentId()),
                () -> assertEquals(employee.getFullTime(), removeEmployee.getFullTime()),
                () -> assertEquals(employee.getSalary(), removeEmployee.getSalary()));
    }

}
