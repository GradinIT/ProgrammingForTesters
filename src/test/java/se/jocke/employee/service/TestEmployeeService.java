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
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(EmployeeDatabaseEntry.builder()
                .employeeId(testPerson.getEmployeeId())
                .firstName(testPerson.getFirstName())
                .lastName(testPerson.getLastName())
                .salary(testPerson.getSalary())
                .fullTime(testPerson.getFullTime())
                .departmentId(testPerson.getDepartmentId())
                .build());
        Employee createdEmployee = crudTest.createEmployee(EmployeePojoMapper.map(testPerson));
        Assertions.assertAll(
                () -> assertNotNull(createdEmployee),
                () -> assertEquals(testPerson.getEmployeeId(), createdEmployee.getEmployeeId().getId()),
                () -> assertEquals(testPerson.getFirstName(), createdEmployee.getFirstName()),
                () -> assertEquals(testPerson.getLastName(), createdEmployee.getLastName()),
                () -> assertEquals(testPerson.getSalary(), createdEmployee.getSalary()),
                () -> assertEquals(testPerson.getFullTime(), createdEmployee.getFullTime()),
                () -> assertEquals(testPerson.getDepartmentId(), createdEmployee.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void testCreateEmployeeAlreadyExistsError() {
        Employee employee = EmployeePojoMapper.map(testPerson);
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(testPerson));
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
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        Throwable errorMessage = assertThrows(EntityNotFoundException.class, () -> crudTest.updateEmployee(EmployeePojoMapper.map(testPerson)));
        Assertions.assertEquals("Entity with id "+testPerson.getEmployeeId()+" not found", errorMessage.getMessage());
        verify(employeeDao, times(1)).findById(any(Integer.class));
        verifyNoMoreInteractions(employeeDao);
    }

    @Test
    public void testRemoveEmployee() {
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(testPerson));
        Employee deletedEmployee = crudTest.removeEmployee(EmployeePojoMapper.map(testPerson));
        Assertions.assertAll(
                () -> assertEquals(deletedEmployee.getEmployeeId().getId(), testPerson.getEmployeeId()),
                () -> assertEquals(deletedEmployee.getFirstName(), testPerson.getFirstName()),
                () -> assertEquals(deletedEmployee.getLastName(), testPerson.getLastName()),
                () -> assertEquals(deletedEmployee.getSalary(), testPerson.getSalary()),
                () -> assertEquals(deletedEmployee.getFullTime(), testPerson.getFullTime()),
                () -> assertEquals(deletedEmployee.getDepartmentId(), testPerson.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, times(1)).delete(any(EmployeeDatabaseEntry.class));

    }

    @Test
    public void testRemoveEmployeeNotFoundException() {
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        Throwable errorMessage = Assertions.assertThrows(EntityNotFoundException.class, () ->
                crudTest.removeEmployee(EmployeePojoMapper.map(testPerson)));
        Assertions.assertEquals("Entity with id "+testPerson.getEmployeeId()+" not found", errorMessage.getMessage());
        verify(employeeDao, times(1)).findById(any(Integer.class));
        // Verifierar att employeeDao körs en gång
        // Båda metoder gör samma sak
        verify(employeeDao, times(0)).delete(any(EmployeeDatabaseEntry.class));
        verifyNoMoreInteractions(employeeDao);
    }

}
