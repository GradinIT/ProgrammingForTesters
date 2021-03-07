package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.dao.EntityAlreadyInStorageException;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.Optional;

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

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("Max")
                .lastName("Martin")
                .salary(BigDecimal.valueOf(45000.0))
                .fullTime(Boolean.TRUE)
                .departmentId(666)
                .build()));
    }

    @Test
    public void findByID(){
        Employee employee = systemUnderTest.getEmployeeById(1);
        Assertions.assertAll(
                ()-> Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                ()-> Assertions.assertEquals("Max", employee.getFirstName()),
                ()-> Assertions.assertEquals("Martin", employee.getLastName()),
                ()-> Assertions.assertEquals(BigDecimal.valueOf(45000.0), employee.getSalary()),
                ()-> Assertions.assertEquals(Boolean.TRUE, employee.getFullTime()),
                ()-> Assertions.assertEquals(666, employee.getDepartmentId())
        );
        verify(employeeDao,times(1)).findById(1);
    }

    @Test
    public void createEmployee () { //Testar vårt Happy Flow :)
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
        Employee createdEmployee = systemUnderTest.createEmployee(employee);
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
    public void createEmployeeError () {
        Employee employee = EmployeeTestBuilder.builder().build();
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(employee.getEmployeeId().getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .salary(employee.getSalary())
                .fullTime(employee.getFullTime())
                .departmentId(employee.getDepartmentId())
                .build()));

        Throwable exception = Assertions.assertThrows(EntityAlreadyInStorageException.class, () -> {
            systemUnderTest.createEmployee(employee);
        });
        Assertions.assertEquals("Entity with id "+employee.getEmployeeId().getId() + " already in storage",
                exception.getMessage());
        verify(employeeDao,times(1)).findById(any(Integer.class));
        verify(employeeDao, never()).save(any()); //Dubbelkollar med verify att testet avslutas (Denna kod var vackrare än NoMoreInteractions)
    }
}