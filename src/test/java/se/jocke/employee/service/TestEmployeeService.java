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
import se.jocke.dao.EntityAlreadyInStorageException;
import se.jocke.dao.EntityNotFoundException;
import se.jocke.dao.mapper.EmployeePojoMapper;
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


    }

    @Test
    public void findById(){
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("Development")
                .lastName("Last")
                .salary(BigDecimal.valueOf(424242424))
                .fullTime(true)
                .departmentId(1)
                .build()));

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
    public void failGetEmployeeById(){
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        Employee employee = EmployeeTestBuilder.builder().build();

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            systemUnderTest.getEmployeeById(employee.getEmployeeId().getId());
        });

        verify(employeeDao,times(1)).findById(any());

    }

    @Test
    public void createEmployeeUnit(){
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());


        when(employeeDao.save(any())).thenReturn((EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("Dave")
                .lastName("Lasto")
                .salary(BigDecimal.valueOf(323232323))
                .fullTime(true)
                .departmentId(1)
                .build()));

        Employee employee = EmployeeTestBuilder.builder().build();

        Employee employeeRe = systemUnderTest.createEmployee(employee);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employeeRe.getEmployeeId().getId()),
                () -> Assertions.assertEquals("Dave", employeeRe.getFirstName()),
                () -> Assertions.assertEquals("Lasto", employeeRe.getLastName()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(323232323), employeeRe.getSalary()),
                () -> Assertions.assertEquals(true, employeeRe.getFullTime()),
                () -> Assertions.assertEquals(1, employeeRe.getDepartmentId()));
        verify(employeeDao,times(1)).findById(employee.getEmployeeId().getId());
        verify(employeeDao,times(1)).save(any());

    }
    @Test
    public void createEmployeeTestFail(){
        when(employeeDao.findById(any())).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(2)
                .firstName("adasdr")
                .lastName("grtrrrt")
                .salary(BigDecimal.valueOf(2342))
                .fullTime(true)
                .departmentId(32)
                .build()));
        Assertions.assertThrows(EntityAlreadyInStorageException.class, ()->{
           systemUnderTest.createEmployee(EmployeeTestBuilder.builder().build());
        });
        verify(employeeDao,times(1)).findById(any());
    }
    @Test
    public void removeEmployeeTest(){
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("Development")
                .lastName("Last")
                .salary(BigDecimal.valueOf(424242424))
                .fullTime(true)
                .departmentId(1)
                .build()));

        Employee employee = EmployeeTestBuilder.builder().build();
        Employee emplRe = systemUnderTest.removeEmployee(employee);

        Assertions.assertEquals(emplRe,emplRe);

        verify(employeeDao,times(1)).findById(100);
        verify(employeeDao,times(1)).delete(any());

    }
    @Test
    public void removeEmployeeTestFail(){
        when(employeeDao.findById(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class,()->{
            systemUnderTest.removeEmployee(EmployeeTestBuilder.builder().build());
        });
        verify(employeeDao,times(1)).findById(any());
    }
    @Test
    public void updateEmployeeTest(){
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("Development")
                .lastName("Last")
                .salary(BigDecimal.valueOf(424242424))
                .fullTime(true)
                .departmentId(1)
                .build()));
        when(employeeDao.save(any())).thenReturn((EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("Dave")
                .lastName("Lasto")
                .salary(BigDecimal.valueOf(323232323))
                .fullTime(true)
                .departmentId(1)
                .build()));


        Employee employee = EmployeeTestBuilder.builder().build();
        Employee empl = systemUnderTest.updateEmployee(employee);

       Assertions.assertAll(
               ()->Assertions.assertEquals(1,empl.getEmployeeId().getId()),
               ()->Assertions.assertEquals("Dave",empl.getFirstName()),
               ()->Assertions.assertEquals("Lasto",empl.getLastName()),
               ()->Assertions.assertEquals(BigDecimal.valueOf(323232323),empl.getSalary()),
               ()->Assertions.assertEquals(true,empl.getFullTime()),
               ()->Assertions.assertEquals(1,empl.getDepartmentId())
       );


    }
    @Test
    public void failUpdateEmployeeTest(){
        Employee employee = EmployeeTestBuilder.builder().build();

        when(employeeDao.findById(employee.getEmployeeId().getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            systemUnderTest.updateEmployee(employee);
        });

        verify(employeeDao,times(1)).findById(any());
        verify(employeeDao,times(0)).save(any());
    }

    @Test
    public void getAllEmployeeTest(){
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

        when(employeeDao.findAll()).thenReturn(emplList);

       List<Employee> emplist = systemUnderTest.getAllEmployees();
       Assertions.assertEquals(2,emplist.size());
       verify(employeeDao, times(1)).findAll();

    }

}
