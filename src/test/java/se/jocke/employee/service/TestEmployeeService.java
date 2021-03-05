package se.jocke.employee.service;

import com.sun.xml.bind.v2.model.core.ID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.dao.EntityNotFoundException;
import se.jocke.dao.mapper.EmployeePojoMapper;
import se.jocke.department.entity.Employee;
import se.jocke.department.entity.EmployeeID;
import se.jocke.department.entity.Entity;
import se.jocke.employee.builder.EmployeeModelTestBuilder;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {

    Employee globalEmployee;

    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeService employeeService = new EmployeeServiceImpl();

    @BeforeEach
    public void setup() {

        globalEmployee = EmployeeTestBuilder.builder()
                .employeeId(EmployeeID.builder().id(1).build())
                .firstName("Linus")
                .lastName("Hellberg")
                .fullTime(false)
                .salary(new BigDecimal("22000.00"))
                .departmentId(3)
                .build();
    }

    @Test
    public void testFindById() {

        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(globalEmployee.getEmployeeId().getId())
                .firstName(globalEmployee.getFirstName())
                .lastName(globalEmployee.getLastName())
                .fullTime(globalEmployee.getFullTime())
                .salary(globalEmployee.getSalary())
                .departmentId(globalEmployee.getDepartmentId())
                .build()));

        Employee emp = employeeService.getEmployeeById(1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(globalEmployee.getEmployeeId().getId(),emp.getEmployeeId().getId()),
                () -> Assertions.assertEquals(globalEmployee.getFirstName(),emp.getFirstName()),
                () -> Assertions.assertEquals(globalEmployee.getLastName(),emp.getLastName()),
                () -> Assertions.assertEquals(globalEmployee.getFullTime(),emp.getFullTime()),
                () -> Assertions.assertEquals(globalEmployee.getSalary(),emp.getSalary()),
                () -> Assertions.assertEquals(globalEmployee.getDepartmentId(),emp.getDepartmentId())
                );

        verify(employeeDao,times(1)).findById(1);
    }

    @Test
    public void testFindAllEmployees() {

        when(employeeDao.findAll()).thenReturn(Arrays.asList(
                EmployeeDatabaseEntry.builder()
                        .employeeId(globalEmployee.getEmployeeId().getId())
                        .firstName(globalEmployee.getFirstName())
                        .lastName(globalEmployee.getLastName())
                        .fullTime(globalEmployee.getFullTime())
                        .salary(globalEmployee.getSalary())
                        .departmentId(globalEmployee.getDepartmentId())
                        .build()));

        List<Employee> emps = employeeService.getAllEmployees();

        Assertions.assertAll(
                () -> Assertions.assertTrue(!emps.isEmpty()),
                () -> Assertions.assertEquals(1,emps.size()));

        verify(employeeDao,times(1)).findAll();
    }

    @Test
    public void testCreateEmployee() {

        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(EmployeeDatabaseEntry.builder()
                .employeeId(globalEmployee.getEmployeeId().getId())
                .firstName(globalEmployee.getFirstName())
                .lastName(globalEmployee.getLastName())
                .fullTime(globalEmployee.getFullTime())
                .salary(globalEmployee.getSalary())
                .departmentId(globalEmployee.getDepartmentId())
                .build());

        Employee emp = employeeService.createEmployee(globalEmployee);

        Assertions.assertAll(
                () -> Assertions.assertEquals(globalEmployee.getEmployeeId().getId(),emp.getEmployeeId().getId()),
                () -> Assertions.assertEquals(globalEmployee.getFirstName(),emp.getFirstName()),
                () -> Assertions.assertEquals(globalEmployee.getLastName(),emp.getLastName()),
                () -> Assertions.assertEquals(globalEmployee.getFullTime(),emp.getFullTime()),
                () -> Assertions.assertEquals(globalEmployee.getSalary(),emp.getSalary()),
                () -> Assertions.assertEquals(globalEmployee.getDepartmentId(),emp.getDepartmentId()));

        verify(employeeDao,times(1)).findById(any(Integer.class));
        verify(employeeDao,times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void testUpdateEmployee() {
        globalEmployee = EmployeeTestBuilder.builder()
                .employeeId(globalEmployee.getEmployeeId())
                .firstName(globalEmployee.getFirstName())
                .lastName("Furu")
                .fullTime(globalEmployee.getFullTime())
                .salary(globalEmployee.getSalary())
                .departmentId(globalEmployee.getDepartmentId())
                .build();

        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeePojoMapper.map(globalEmployee)));
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(EmployeePojoMapper.map(globalEmployee));

        Employee updatedEmp = employeeService.updateEmployee(globalEmployee);

        Assertions.assertAll(
                () -> Assertions.assertEquals(globalEmployee.getEmployeeId().getId(),updatedEmp.getEmployeeId().getId()),
                () -> Assertions.assertEquals(globalEmployee.getFirstName(),updatedEmp.getFirstName()),
                () -> Assertions.assertEquals(globalEmployee.getLastName(),updatedEmp.getLastName()),
                () -> Assertions.assertEquals(globalEmployee.getFullTime(),updatedEmp.getFullTime()),
                () -> Assertions.assertEquals(globalEmployee.getSalary(),updatedEmp.getSalary()),
                () -> Assertions.assertEquals(globalEmployee.getDepartmentId(),updatedEmp.getDepartmentId())
        );

        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void testDeleteEmployee() {

        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeePojoMapper.map(globalEmployee)));

        Employee emp = employeeService.removeEmployee(globalEmployee);

        Assertions.assertAll(
                () -> Assertions.assertEquals(globalEmployee.getEmployeeId().getId(),emp.getEmployeeId().getId()),
                () -> Assertions.assertEquals(globalEmployee.getFirstName(),emp.getFirstName()),
                () -> Assertions.assertEquals(globalEmployee.getLastName(),emp.getLastName()),
                () -> Assertions.assertEquals(globalEmployee.getFullTime(),emp.getFullTime()),
                () -> Assertions.assertEquals(globalEmployee.getSalary(),emp.getSalary()),
                () -> Assertions.assertEquals(globalEmployee.getDepartmentId(),emp.getDepartmentId())
        );

        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, times(1)).delete(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void testEntityNotFoundException() {
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());

        Throwable t = Assertions.assertThrows(EntityNotFoundException.class,
                () -> employeeService.removeEmployee(globalEmployee));

        Assertions.assertEquals("Entity with id " + globalEmployee.getEmployeeId().getId() + " not found",t.getMessage());
    }
}
