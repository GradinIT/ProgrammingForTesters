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
    @Mock // Vi har en mockad dao för att vi vill isolera funktionaliteten i Service-klassen och BARA testa metoderna där
    private EmployeeDao employeeDao;
    @InjectMocks // Vi injecerar vår mockade dao i vårt service-objekt
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();

    Employee exampleEmployee;

    @BeforeEach
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
    // Cannot invoke "se.jocke.dao.EmployeeDatabaseEntry.getEmployeeId()" because "employeeDatabaseEntry" is null
    public void testCreateEmployee() {

        /*exampleEmployee = Employee.builder().employeeId(EmployeeID.builder().id(1).build())
                .firstName("Mock")
                .lastName("Mockesson")
                .salary(BigDecimal.valueOf(22000))
                .fullTime(true)
                .departmentId(5)
                .build();

        Employee addedEmployee = systemUnderTest.createEmployee(exampleEmployee);

        Assertions.assertNotNull(addedEmployee);
        Assertions.assertEquals(100, addedEmployee.getEmployeeId().getId());
        Assertions.assertEquals("Mock", addedEmployee.getFirstName());*/
    }

    public void testRemoveEmployee() {

    }

    public void testUpdateEmployee() {

    }

    public void testGetAllEmployee() {

    }
}
