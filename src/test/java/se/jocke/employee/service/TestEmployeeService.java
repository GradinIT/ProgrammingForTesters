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

    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeService employeeService = new EmployeeServiceImpl();

    @BeforeEach
    public void setup() {

//        final Integer employeeId = 1;
//        String firstName = "Linus";
//        String lastName = "Hellberg";
//        boolean fulltime = false;
//        BigDecimal salary = new BigDecimal("22000.00");
//        Integer departmendId = 3;

    }

    @Test
    public void findById() {

        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("Linus")
                .lastName("Hellberg")
                .fullTime(false)
                .salary(new BigDecimal(22000.00))
                .departmentId(3)
                .build()));

        Employee emp = employeeService.getEmployeeById(1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1,emp.getEmployeeId().getId()),
                () -> Assertions.assertEquals("Linus",emp.getFirstName()),
                () -> Assertions.assertEquals("Hellberg",emp.getLastName()),
                () -> Assertions.assertEquals(false,emp.getFullTime()),
                () -> Assertions.assertEquals(new BigDecimal(22000.00),emp.getSalary()),
                () -> Assertions.assertEquals(3,emp.getDepartmentId())
                );

        verify(employeeDao,times(1)).findById(1);
    }

    @Test
    public void findAllEmployees() {

        when(employeeDao.findAll()).thenReturn(Arrays.asList(
                EmployeeDatabaseEntry.builder()
                        .employeeId(1)
                        .firstName("Linus")
                        .lastName("Hellberg")
                        .fullTime(false)
                        .salary(new BigDecimal(22000.00))
                        .departmentId(3)
                        .build()));

        List<Employee> emps = employeeService.getAllEmployees();

        Assertions.assertAll(
                () -> Assertions.assertTrue(!emps.isEmpty()),
                () -> Assertions.assertEquals(1,emps.size()));

        verify(employeeDao,times(1)).findAll();
    }

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

        Employee emp = employeeService.createEmployee(employee);

        Assertions.assertAll(
                () -> Assertions.assertEquals(employee.getEmployeeId().getId(),emp.getEmployeeId().getId()),
                () -> Assertions.assertEquals(employee.getFirstName(),emp.getFirstName()),
                () -> Assertions.assertEquals(employee.getLastName(),emp.getLastName()),
                () -> Assertions.assertEquals(employee.getFullTime(),emp.getFullTime()),
                () -> Assertions.assertEquals(employee.getSalary(),emp.getSalary()),
                () -> Assertions.assertEquals(employee.getDepartmentId(),emp.getDepartmentId()));

        verify(employeeDao,times(1)).findById(any(Integer.class));
        verify(employeeDao,times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void updateEmployee() {
        Employee employee = EmployeeTestBuilder.builder().lastName("Hellberg").build();

        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeePojoMapper.map(employee)));
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(EmployeePojoMapper.map(employee));

        Employee updatedEmp = employeeService.updateEmployee(employee);

        Assertions.assertAll(
                () -> Assertions.assertEquals(employee.getEmployeeId().getId(),updatedEmp.getEmployeeId().getId()),
                () -> Assertions.assertEquals(employee.getFirstName(),updatedEmp.getFirstName()),
                () -> Assertions.assertEquals(employee.getLastName(),updatedEmp.getLastName()),
                () -> Assertions.assertEquals(employee.getFullTime(),updatedEmp.getFullTime()),
                () -> Assertions.assertEquals(employee.getSalary(),updatedEmp.getSalary()),
                () -> Assertions.assertEquals(employee.getDepartmentId(),updatedEmp.getDepartmentId())
        );

        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void deleteEmployee() {
        Employee employee = EmployeeTestBuilder.builder().build();

        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeePojoMapper.map(employee)));

        Employee emp = employeeService.removeEmployee(employee);

        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, times(1)).delete(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void testEntityNotFoundException() {
        Employee emp = EmployeeTestBuilder.builder().build();
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());

        Throwable t = Assertions.assertThrows(EntityNotFoundException.class,
                () -> employeeService.removeEmployee(emp));

        Assertions.assertEquals("Entity with id " + emp.getEmployeeId().getId() + " not found",t.getMessage());
    }
}
