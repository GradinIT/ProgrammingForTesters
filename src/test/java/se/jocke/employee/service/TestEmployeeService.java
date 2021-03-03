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
    //De som vi mockar
    @Mock
    private EmployeeDao employeeDao;

    //Det som ska testas
    @InjectMocks
    private final EmployeeService systemUndertest = new EmployeeServiceImpl();

    //Används för att hålla isär testet och miljöuppsättningen.
    //Körs före varje annoterad metod @test.
    @BeforeEach
    public void setUp() {

    }

   //Testar getEmployeeById(Integer employee)
    @Test
    public void findByEmployeeId() {
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
        //Kollar så att anropet har kommit till den metoden,
        //speciellt eftersom vi har injectad
        verify(employeeDao, times(1)).findById(1);
    }

//  Testar List<Employee> getAllEmployees()
    @Test
    public void testListOfEmployees() {
        //Sätt upp regel
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
                ()-> assertNotNull(employees),
                ()-> assertEquals(1, employees.size())
        );
//        List<Employee> mockedList = mock(List.class);
//        mockedList.size();
//        verify(mockedList, times(1)).size();
    }

    //Testar updateEmployee(Employee employee), normalflödet (Happy flow)
    @Test
    public void createEmployee() {
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

//    @Test
//    public void updateEmployee() {
//        Employee employee = EmployeeTestBuilder.builder().build();
//        Employee updateEmployee = systemUndertest.updateEmployee(employee);
//        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());
//        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(EmployeeDatabaseEntry.builder()
//                .employeeId(employee.getEmployeeId().getId())
//                .firstName(employee.getFirstName())
//                .lastName(employee.getLastName())
//                .fullTime(employee.getFullTime())
//                .salary(employee.getSalary())
//                .departmentId(employee.getDepartmentId())
//                .build());
//
//        Assertions.assertAll(
//                () -> assertNotNull(updateEmployee),
//                () -> assertEquals(employee.getEmployeeId().getId(), updateEmployee.getEmployeeId().getId()),
//                () -> assertEquals(employee.getLastName(), updateEmployee.getLastName()),
//                () -> assertEquals(employee.getFirstName(), updateEmployee.getFirstName()),
//                () -> assertEquals(employee.getDepartmentId(), updateEmployee.getDepartmentId()),
//                () -> assertEquals(employee.getFullTime(), updateEmployee.getFullTime()),
//                () -> assertEquals(employee.getSalary(), updateEmployee.getSalary()));
//        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
//        verify(employeeDao, times(2)).findById(any(Integer.class));
//
//    }

}
