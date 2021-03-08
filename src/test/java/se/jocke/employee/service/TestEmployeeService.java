package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.api.mapper.EmployeeModelMapper;
import se.jocke.dao.*;
import se.jocke.dao.mapper.EmployeePojoMapper;
import se.jocke.department.entity.Department;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeModelTestBuilder;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {

    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();

    Employee testEmployee;
    @BeforeEach
    public void setUp(){
        testEmployee = EmployeeTestBuilder.builder().build();
    }

    @Test
    public void findByIdHappyFlow(){
        when(employeeDao.findById(testEmployee.getEmployeeId().getId()))
                .thenReturn(Optional.of(EmployeePojoMapper.map(testEmployee)));

        Employee employee = systemUnderTest.getEmployeeById(testEmployee.getEmployeeId().getId());
        Assertions.assertAll(
                () -> assertNotNull(employee),
                () -> Assertions.assertEquals(EmployeeModelMapper.map(employee), EmployeeModelMapper.map(testEmployee))

        );
        verify(employeeDao, times(1)).findById(testEmployee.getEmployeeId().getId());

    }
    @Test
    public void findByIdException(){
        when(employeeDao.findById(testEmployee.getEmployeeId().getId())).thenReturn(Optional.empty());

        Throwable exception = Assertions.assertThrows(EntityNotFoundException.class, () -> systemUnderTest.getEmployeeById(testEmployee.getEmployeeId().getId())
        );
        verify(employeeDao , times(1)).findById(testEmployee.getEmployeeId().getId());
        Assertions.assertEquals("Entity with id " + testEmployee.getEmployeeId().getId() + " not found", exception.getMessage());
    }

    @Test
    public void createEmployeeHappyFlow(){

        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        when(employeeDao.save(EmployeePojoMapper.map(testEmployee))).thenReturn(EmployeePojoMapper.map(testEmployee));

        Employee savedEmployee = systemUnderTest.createEmployee(testEmployee);
        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao,times(1)).save(any(EmployeeDatabaseEntry.class));

        Assertions.assertEquals(EmployeeModelMapper.map(testEmployee), EmployeeModelMapper.map(savedEmployee));

    }

    @Test
    public void createEmployeeError(){

        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeePojoMapper.map(testEmployee)));

        Throwable exception = Assertions.assertThrows(EntityAlreadyInStorageException.class, () -> {
            systemUnderTest.createEmployee(testEmployee);
        });

        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, times(0)).save(any(EmployeeDatabaseEntry.class));

        Assertions.assertEquals("Entity with id " + testEmployee.getEmployeeId().getId() + " already in storage",exception.getMessage());
    }

    @Test
    public void removeEmployeeHappyFlow(){

        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeePojoMapper.map(testEmployee)));

        Assertions.assertEquals(testEmployee, systemUnderTest.removeEmployee(testEmployee));
        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, times(1)).delete(EmployeePojoMapper.map(testEmployee));
    }

    @Test
    public void removeEmployeeError(){

        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> systemUnderTest.removeEmployee(testEmployee));
        verify(employeeDao, times(1)).findById(any(Integer.class));
        verifyNoMoreInteractions(employeeDao);
    }

    @Test
    public void updateEmployeeHappyFlow(){
        Employee updatedEmployee = EmployeeTestBuilder.builder().firstName("newName").build();

        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeePojoMapper.map(testEmployee)));
        when(employeeDao.save(EmployeePojoMapper.map(updatedEmployee))).thenReturn(EmployeePojoMapper.map(updatedEmployee));
        Employee e = systemUnderTest.updateEmployee(updatedEmployee);
        Assertions.assertAll(
                () -> assertNotNull(e),
                () -> assertTrue(testEmployee != updatedEmployee),
                () -> assertEquals(e.getFirstName(), updatedEmployee.getFirstName())
        );

        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void updateEmployeeError(){
       Integer employeeId = testEmployee.getEmployeeId().getId();

        when(employeeDao.findById(employeeId)).thenReturn(Optional.empty());
        Throwable exception = Assertions.assertThrows(EntityNotFoundException.class, () ->
                { systemUnderTest.updateEmployee(testEmployee); }
        );
        verify(employeeDao, times(1)).findById(employeeId);
        verifyNoMoreInteractions(employeeDao);
        Assertions.assertEquals("Entity with id "+ employeeId +" not found",exception.getMessage());
    }
    @Test
    public void getAll(){

        when(employeeDao.findAll()).thenReturn(Arrays.asList(EmployeePojoMapper.map(testEmployee)));

        List<Employee> employees = systemUnderTest.getAllEmployees();
        Assertions.assertAll(
                () -> assertNotNull(employees),
                () -> assertEquals(1, employees.size())
        );

    }


}
