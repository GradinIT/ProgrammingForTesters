package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.*;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {

    // skapar en fejk utav Employee dao
    @Mock
    private EmployeeDao employeeDao;

    // lägger in fejk employeeDao i EmployeeServiceImpl
    @InjectMocks//-------------------------------|´´´´´´´´´´´´´´´´´´´´´|
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();

    @BeforeEach
    public void setUp() {




    }

    @Test
    public void findById() {
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("Robert")
                .lastName("Tenglund")
                .salary(BigDecimal.valueOf(50000))
                .fullTime(true)
                .departmentId(1)
                .build()));

        Employee employee = systemUnderTest.getEmployeeById(1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("Robert", employee.getFirstName())
                // lägg in resten utav variablerna i testet
        );
        // verifierar att mocken har använts 1 gång
        verify(employeeDao, times(1)).findById(1);
    }

    @Test
    public void getAllEmployees() {

        when(employeeDao.findAll()).thenReturn(Arrays.asList(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("Blurb")
                .lastName("Glurb")
                .salary(BigDecimal.valueOf(60000))
                .fullTime(true)
                .departmentId(1)
                .build()));

        List<Employee> employees = systemUnderTest.getAllEmployees();
        System.out.println("---------------------------" + employees);
        Assertions.assertAll(
                () -> assertNotNull(employees),
                () -> assertEquals(1, employees.size())
        );
    }

    @Test
    public void createEmployeeHappyFlow() {

        //ser om vi kan skapa en employee

        Employee employee = EmployeeTestBuilder.builder().build();
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(EmployeeDatabaseEntry.builder()
                .employeeId(employee.getEmployeeId().getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .salary(employee.getSalary())
                .fullTime(employee.getFullTime())
                .departmentId(employee.getDepartmentId())
                .build());
        Employee createdEmployee = systemUnderTest.createEmployee(employee);
        Assertions.assertAll(
                () -> assertNotNull(createdEmployee),
                () -> assertEquals(employee.getDepartmentId(), createdEmployee.getDepartmentId()),
                () -> assertEquals(employee.getFirstName(), createdEmployee.getFirstName())
        );
        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void createEmployeeError() {
        Employee employee = EmployeeTestBuilder.builder().build();
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(
                EmployeeDatabaseEntry.builder()
                        .employeeId(employee.getEmployeeId().getId())
                        .firstName(employee.getFirstName())
                        .lastName(employee.getLastName())
                        .salary(employee.getSalary())
                        .fullTime(employee.getFullTime())
                        .departmentId(employee.getDepartmentId())
                        .build()));

        Throwable exception = Assertions.assertThrows(EntityAlreadyInStorageException.class, () -> {
            systemUnderTest.createEmployee(employee);
        });
        Assertions.assertEquals("Entity with id "+employee.getEmployeeId().getId()+" already in storage",
                exception.getMessage());
        verify(employeeDao, times(1)).findById(any(Integer.class));
        verifyNoMoreInteractions(employeeDao);
    }
}

