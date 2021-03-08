package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
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
@RunWith(JUnitPlatform.class)
public class TestEmployeeService {
    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();

    @Test
    public void getEmployeeByIdTest() {

        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("fred")
                .lastName("lind")
                .salary(BigDecimal.valueOf(44))
                .fullTime(true)
                .departmentId(4)
                .build()));

        Employee employee = systemUnderTest.getEmployeeById(1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("fred", employee.getFirstName()),
                () -> Assertions.assertEquals("lind", employee.getLastName()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(44), employee.getSalary()),
                () -> Assertions.assertEquals(true, employee.getFullTime()),
                () -> Assertions.assertEquals(4, employee.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(1);
    }

    @Test
    public void getEmployeeByIdFailTest() {

        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            systemUnderTest.getEmployeeById(789);
        });
        verify(employeeDao, times(1)).findById(789);
    }

    @Test
    public void createEmployeeTest() {
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        when(employeeDao.save(any())).thenReturn((EmployeeDatabaseEntry.builder()
                .employeeId(333)
                .firstName("Clark")
                .lastName("Kent")
                .salary(BigDecimal.valueOf(44))
                .fullTime(true)
                .departmentId(4)
                .build()));

        Employee employee = EmployeeTestBuilder.builder().build();
        Employee returnEmp = systemUnderTest.createEmployee(employee);

        Assertions.assertAll(
                () -> Assertions.assertEquals(333, returnEmp.getEmployeeId().getId()),
                () -> Assertions.assertEquals("Clark", returnEmp.getFirstName()),
                () -> Assertions.assertEquals("Kent", returnEmp.getLastName()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(44), returnEmp.getSalary()),
                () -> Assertions.assertEquals(true, returnEmp.getFullTime()),
                () -> Assertions.assertEquals(4, returnEmp.getDepartmentId()));
        verify(employeeDao, times(1)).findById(employee.getEmployeeId().getId());
        verify(employeeDao, times(1)).save(any());

    }

    @Test
    public void createEmployeeFailTest() {

        when(employeeDao.findById(any())).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("fred")
                .lastName("lind")
                .salary(BigDecimal.valueOf(44))
                .fullTime(true)
                .departmentId(4)
                .build()));

        Assertions.assertThrows(EntityAlreadyInStorageException.class, () -> {
            systemUnderTest.createEmployee(EmployeeTestBuilder.builder().build());
        });
        verify(employeeDao, times(1)).findById(any());
    }

    @Test
    public void removeEmployeeByTest() {
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("fred")
                .lastName("lind")
                .salary(BigDecimal.valueOf(44))
                .fullTime(true)
                .departmentId(4)
                .build()));


        Employee employee = EmployeeTestBuilder.builder().build();
        Employee returnEmp = systemUnderTest.removeEmployee(employee);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(returnEmp),
                () -> Assertions.assertEquals(employee.getEmployeeId().getId(), returnEmp.getEmployeeId().getId()),
                () -> Assertions.assertEquals(employee.getFirstName(), returnEmp.getFirstName()),
                () -> Assertions.assertEquals(employee.getLastName(), returnEmp.getLastName()),
                () -> Assertions.assertEquals(employee.getSalary(), returnEmp.getSalary()),
                () -> Assertions.assertEquals(employee.getFullTime(), returnEmp.getFullTime()),
                () -> Assertions.assertEquals(employee.getDepartmentId(), returnEmp.getDepartmentId()));
        verify(employeeDao, times(1)).findById(100);
        verify(employeeDao, times(1)).delete(any());
    }

    @Test
    public void removeEmployeeFailTest() {

        when(employeeDao.findById(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            systemUnderTest.removeEmployee(EmployeeTestBuilder.builder().build());
        });
        verify(employeeDao, times(1)).findById(any());
        verify(employeeDao, times(0)).delete(any());
    }

    @Test
    public void updateEmployeeTest() {
        Employee updEmployee = EmployeeTestBuilder.builder()
                .employeeId(EmployeeID.builder().id(77).build())
                .firstName("Erica")
                .lastName("Jones")
                .salary(BigDecimal.valueOf(44123))
                .fullTime(true)
                .departmentId(4)
                .build();

        when(employeeDao.findById(updEmployee.getEmployeeId().getId())).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(updEmployee.getEmployeeId().getId())
                .firstName("Eric")
                .lastName("Jones")
                .salary(BigDecimal.valueOf(33333))
                .fullTime(true)
                .departmentId(8)
                .build()));
        when(employeeDao.save(any())).thenReturn((EmployeeDatabaseEntry.builder()
                .employeeId(updEmployee.getEmployeeId().getId())
                .firstName("Erica")
                .lastName("Jones")
                .salary(BigDecimal.valueOf(44123))
                .fullTime(true)
                .departmentId(4)
                .build()));

        Employee returnEmp = systemUnderTest.updateEmployee(updEmployee);
        Assertions.assertAll(
                () -> Assertions.assertEquals(updEmployee.getEmployeeId().getId(), returnEmp.getEmployeeId().getId()),
                () -> Assertions.assertEquals(updEmployee.getFirstName(), returnEmp.getFirstName()),
                () -> Assertions.assertEquals(updEmployee.getLastName(), returnEmp.getLastName()),
                () -> Assertions.assertEquals(updEmployee.getSalary(), returnEmp.getSalary()),
                () -> Assertions.assertEquals(updEmployee.getFullTime(), returnEmp.getFullTime()),
                () -> Assertions.assertEquals(updEmployee.getDepartmentId(), returnEmp.getDepartmentId()));
        verify(employeeDao, times(1)).findById(77);
        verify(employeeDao, times(1)).save(any());
    }

    @Test
    public void updateEmployeeFailTest() {
        Employee updEmployee = EmployeeTestBuilder.builder()
                .employeeId(EmployeeID.builder().id(Integer.MAX_VALUE).build())
                .firstName("Erica")
                .lastName("Jones")
                .salary(BigDecimal.valueOf(44123))
                .fullTime(false)
                .departmentId(Integer.MIN_VALUE)
                .build();
        when(employeeDao.findById(updEmployee.getEmployeeId().getId())).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            systemUnderTest.updateEmployee(updEmployee);
        });
        verify(employeeDao, times(1)).findById(Integer.MAX_VALUE);
    }

    @Test
    public void getAllEmployeesTest() {
        List<EmployeeDatabaseEntry> employerDBList = new ArrayList<>();
        int nameIndex = 1;
        int salary = 37777;
        for (int i = 0; i < 3; i++) {
            employerDBList.add(EmployeeDatabaseEntry.builder()
                    .employeeId(6)
                    .firstName("David" + nameIndex)
                    .lastName("Smith" + nameIndex++)
                    .salary(BigDecimal.valueOf((salary++)))
                    .fullTime(true)
                    .departmentId(1000 - i)
                    .build());
        }

        when(employeeDao.findAll()).thenReturn(employerDBList);

        List<Employee> employeeList = systemUnderTest.getAllEmployees();
        Assertions.assertAll(
                () -> Assertions.assertEquals(3, employeeList.size()),
                () -> Assertions.assertEquals("David3", employeeList.get(2).getFirstName()),
                () -> Assertions.assertEquals("Smith1", employeeList.get(0).getLastName()),
                () -> Assertions.assertEquals("Smith1", employeeList.get(0).getLastName()),
                () -> Assertions.assertEquals(1000 - 1, employeeList.get(1).getDepartmentId()));
        verify(employeeDao, times(1)).findAll();
    }
}
