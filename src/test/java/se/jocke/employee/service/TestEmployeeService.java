package se.jocke.employee.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.api.mapper.EmployeeModelMapper;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.dao.mapper.EmployeePojoMapper;
import se.jocke.entity.Employee;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {

    Optional<EmployeeDatabaseEntry> databaseEntryOptional;

    @Mock
    private EmployeeDao employeeDao;
    @InjectMocks
    private EmployeeService crudOperation = new EmployeeServiceImpl();

    @BeforeEach
    public void setUp() {
         databaseEntryOptional = Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("Hanna")
                .lastName("Olsson")
                .salary(BigDecimal.valueOf(40000))
                .fullTime(true)
                .departmentId(1)
                .build());
        MockitoAnnotations.initMocks(this);
        when(employeeDao.findById(any(Integer.class))).thenReturn(databaseEntryOptional);

    }

    @Test
    public void findById() {
        Employee employee = crudOperation.getEmployeeById(1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("Hanna", employee.getFirstName()),
                () -> Assertions.assertEquals("Olsson", employee.getLastName()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(40000), employee.getSalary()),
                () -> Assertions.assertEquals(1, employee.getDepartmentId()),
                () -> Assertions.assertEquals(true, employee.getFullTime())
        );

        verify(employeeDao, times(1)).findById(anyInt());
    }

    @Test
    public void create() {
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(databaseEntryOptional.get());

        Employee employee = crudOperation.createEmployee(crudOperation.getEmployeeById(1));

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("Hanna", employee.getFirstName()),
                () -> Assertions.assertEquals("Olsson", employee.getLastName()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(40000), employee.getSalary()),
                () -> Assertions.assertEquals(1, employee.getDepartmentId()),
                () -> Assertions.assertEquals(true, employee.getFullTime())
        );

        verify(employeeDao, times(1)).save(EmployeePojoMapper.map(employee));
    }

    @Test
    public void update() {

        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(databaseEntryOptional.get());


        Employee employee = EmployeeModelMapper.map(EmployeeModelMapper.map(crudOperation.updateEmployee(crudOperation.getEmployeeById(1))));

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("Hanna", employee.getFirstName()),
                () -> Assertions.assertEquals("Olsson", employee.getLastName()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(40000), employee.getSalary()),
                () -> Assertions.assertEquals(1, employee.getDepartmentId()),
                () -> Assertions.assertEquals(true, employee.getFullTime())
        );

        verify(employeeDao, times(1)).save(EmployeePojoMapper.map(employee));
    }

    @Test
    public void remove() {
        Employee employee = EmployeeModelMapper.map(EmployeeModelMapper.map(crudOperation.removeEmployee(crudOperation.getEmployeeById(1))));

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("Hanna", employee.getFirstName()),
                () -> Assertions.assertEquals("Olsson", employee.getLastName()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(40000), employee.getSalary()),
                () -> Assertions.assertEquals(1, employee.getDepartmentId()),
                () -> Assertions.assertEquals(true, employee.getFullTime())
        );

        verify(employeeDao, times(1)).delete(EmployeePojoMapper.map(employee));
    }

    @Test
    public void getAll() {

        List<Employee> employees = crudOperation.getAllEmployees();

        Assertions.assertAll(
                () -> Assertions.assertEquals(crudOperation.getAllEmployees().size(), employees.size()),
                () -> Assertions.assertEquals(crudOperation.getAllEmployees(), employees));

        verify(employeeDao, times(3)).findAll();

    }

}