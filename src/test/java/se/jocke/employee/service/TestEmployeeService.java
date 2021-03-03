package se.jocke.employee.service;

import org.junit.Before;
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
import se.jocke.department.entity.EmployeeID;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {
    @Mock // Varför
    private EmployeeDao employeeDao;
    @InjectMocks
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();

    Employee exampleEmployee;

    // Utöka med att testa de andra metoderna i EmployeeServiceImpl?

    @BeforeEach // Forska lite kring detta - en setup för varje test eller köra samma flera gånger?
    public void setUp() {
        // När vi anropar findById() med vilket id som helst skapas istället en påhittad employee
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("Mock")
                .lastName("Mockesson")
                .salary(BigDecimal.valueOf(22000))
                .fullTime(true)
                .departmentId(5)
                .build()));
    }

    @Test
    public void testGetEmployeeById() {
        Employee employee = systemUnderTest.getEmployeeById(1);

        Assertions.assertEquals(1, employee.getEmployeeId().getId());
        Assertions.assertEquals("Mock", employee.getFirstName());

        verify(employeeDao, times(1)).findById(1); // Vad gör verify? Varför vill vi veta hur många gånger findById körs?
    }

//    @Before
//    public void employeeToAdd() {
//        exampleEmployee = Employee.builder().employeeId(EmployeeID.builder().id(1).build())
//                .firstName("Mock")
//                .lastName("Mockesson")
//                .salary(BigDecimal.valueOf(22000))
//                .fullTime(true)
//                .departmentId(5)
//                .build();
//    }
    @Test
    public void testCreateEmployee() {
        exampleEmployee = Employee.builder().employeeId(EmployeeID.builder().id(100).build())
                .firstName("Mock")
                .lastName("Mockesson")
                .salary(BigDecimal.valueOf(22000))
                .fullTime(true)
                .departmentId(5)
                .build();

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
        Assertions.assertEquals(100, addedEmployee.getEmployeeId().getId());
        Assertions.assertEquals("Mock", addedEmployee.getFirstName());
        verify(employeeDao,times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    public void testRemoveEmployee() {

    }

    public void testUpdateEmployee() {

    }

    public void testGetAllEmployee() {

    }
}
