package se.jocke.employee.service;

import io.cucumber.java.an.E;
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
import se.jocke.department.entity.EmployeeID;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//@RunWith(JUnitPlatform.class)
public class TestEmployeeService {
    @Mock
    private EmployeeDao employeeDao;
    @InjectMocks
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();


    @BeforeEach
    public void setUp(){
        List<EmployeeDatabaseEntry> emplList = new ArrayList<>();
        emplList.add(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("adad")
                .lastName("dadadad")
                .salary(BigDecimal.valueOf(42242))
                .fullTime(true)
                .departmentId(4)
                .build());
        emplList.add(EmployeeDatabaseEntry.builder()
                .employeeId(2)
                .firstName("adaadadd")
                .lastName("dqeqeqe")
                .salary(BigDecimal.valueOf(4242))
                .fullTime(false)
                .departmentId(4)
                .build());

        lenient().when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("Development")
                .lastName("Last")
                .salary(BigDecimal.valueOf(424242424))
                .fullTime(true)
                .departmentId(1)
                .build()));

        lenient().when(employeeDao.save(any())).thenReturn((EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("Deve")
                .lastName("Lasto")
                .salary(BigDecimal.valueOf(323232323))
                .fullTime(true)
                .departmentId(1)
                .build()));
        lenient().when(employeeDao.findAll()).thenReturn(emplList);



    }

    @Test
    public void findById(){
        Employee employee = systemUnderTest.getEmployeeById(1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals(1, employee.getDepartmentId()),
                () -> Assertions.assertEquals("Development", employee.getFirstName()),
                () -> Assertions.assertEquals("Last", employee.getLastName()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(424242424) , employee.getSalary()),
                () -> Assertions.assertEquals(true, employee.getFullTime())
        );
        verify(employeeDao, times(1)).findById(1);
    }

    @Test
    public void createOrUpdateEmployeeUnit(){
        Employee employee = EmployeeTestBuilder.builder()
                .employeeId(EmployeeID.builder().id(4).build())
                .firstName("buu")
                .lastName("muu")
                .salary(BigDecimal.valueOf(44444))
                .fullTime(true)
                .departmentId(33)
                .build();

        Employee employeeRe = systemUnderTest.createOrUpdateEmployee(employee);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employeeRe.getEmployeeId().getId()),
                () -> Assertions.assertEquals("Deve", employeeRe.getFirstName()),
                () -> Assertions.assertEquals("Lasto", employeeRe.getLastName()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(323232323), employeeRe.getSalary()),
                () -> Assertions.assertEquals(true, employeeRe.getFullTime()),
                () -> Assertions.assertEquals(1, employeeRe.getDepartmentId()));

    }
    @Test
    public void removeEmployeeTest(){
        Employee employee = EmployeeTestBuilder.builder().build();
        Employee emplRe = systemUnderTest.removeEmployee(employee);

        Assertions.assertEquals(employee,emplRe);

    }
    @Test
    public void updateEmployeeTest(){
        Employee employee = EmployeeTestBuilder.builder().build();
        Employee empl = systemUnderTest.updateEmployee(employee);

       // Assertions.assertEquals(employee,empl);
    }

    @Test
    public void getAllEmployeeTest(){
       List<Employee> emplist = systemUnderTest.getAllEmployees();
        System.out.println(emplist.get(0));
       Assertions.assertEquals(2,emplist.size());

    }

}
