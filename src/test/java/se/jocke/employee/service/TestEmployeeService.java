package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import se.jocke.employee.builder.EmployeeModelTestBuilder;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {
    EmployeeDatabaseEntry testPerson;
    @Mock
    private EmployeeDao employeeDao;
    @InjectMocks
    private EmployeeService crudTest = new EmployeeServiceImpl();

    @BeforeEach
    public void setUp() {
        testPerson = EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("Gunnar")
                .lastName("Svensson")
                .fullTime(true)
                .salary(BigDecimal.valueOf(25000))
                .departmentId(1)
                .build();
    }
    @Test
    public void testFindById() {
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(testPerson));
        Employee employee = crudTest.getEmployeeById(1);
        Assertions.assertAll(
                () -> assertEquals(1, employee.getEmployeeId().getId()),
                () -> assertEquals("Gunnar", employee.getFirstName()),
                () -> assertEquals("Svensson", employee.getLastName()),
                () -> assertEquals(true, employee.getFullTime()),
                () -> assertEquals(BigDecimal.valueOf(25000), employee.getSalary()),
                () -> assertEquals(1, employee.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(1);
    }

    @Test
    public void testGetAllEmployees() {
        when(employeeDao.findAll()).thenReturn(Arrays.asList(testPerson));
            List<Employee> employees = crudTest.getAllEmployees();
            Assertions.assertAll(
                    () -> assertNotNull(employees),
                    () -> assertEquals(1, employees.size())
            );
    }

    @Test
    public void testCreateEmployeeHappyFlow() {
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
        Employee createdEmployee = crudTest.createEmployee(employee);
        Assertions.assertAll(
                () -> assertNotNull(createdEmployee),
                () -> assertEquals(employee.getEmployeeId().getId(), createdEmployee.getEmployeeId().getId()),
                () -> assertEquals(employee.getFirstName(), createdEmployee.getFirstName()),
                () -> assertEquals(employee.getLastName(), createdEmployee.getLastName()),
                () -> assertEquals(employee.getSalary(), createdEmployee.getSalary()),
                () -> assertEquals(employee.getFullTime(), createdEmployee.getFullTime()),
                () -> assertEquals(employee.getDepartmentId(), createdEmployee.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void testCreateEmployeeAlreadyExistsError() {
        Employee employee = EmployeeTestBuilder.builder().build();
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(employee.getEmployeeId().getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .salary(employee.getSalary())
                .fullTime(employee.getFullTime())
                .departmentId(employee.getDepartmentId())
                .build()));
        Throwable errorMessage = Assertions.assertThrows(EntityAlreadyInStorageException.class, () -> {
           crudTest.createEmployee(employee);
        });
        Assertions.assertEquals("Entity with id "+employee.getEmployeeId().getId()+" already in storage", errorMessage.getMessage());
        verify(employeeDao, times(1)).findById(any(Integer.class));
        verifyNoMoreInteractions(employeeDao);
    }

    @Test
    public void testUpdateEmployee() {
        BigDecimal newSalary = new BigDecimal(30000);
        Employee employeeToUpdate = EmployeePojoMapper.map(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("Gunnar")
                .lastName("Svensson")
                .fullTime(true)
                .salary(newSalary)
                .departmentId(1)
                .build());
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(testPerson));
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(EmployeePojoMapper.map(employeeToUpdate));

        Employee updatedEmployee = crudTest.updateEmployee(EmployeePojoMapper.map(testPerson));
        Assertions.assertAll(
                () -> assertEquals(employeeToUpdate.getEmployeeId().getId(), updatedEmployee.getEmployeeId().getId()),
                () -> assertEquals(employeeToUpdate.getFirstName(), updatedEmployee.getFirstName()),
                () -> assertEquals(employeeToUpdate.getLastName(), updatedEmployee.getLastName()),
                () -> assertEquals(employeeToUpdate.getFullTime(), updatedEmployee.getFullTime()),
                () -> assertEquals(employeeToUpdate.getSalary(), updatedEmployee.getSalary()),
                () -> assertEquals(employeeToUpdate.getDepartmentId(), updatedEmployee.getDepartmentId())
        );
    }

    @Test
    public void testUpdateEmployeeNotFoundException() {

    }

    @Test
    public void testRemoveEmployee() {


    }

}
