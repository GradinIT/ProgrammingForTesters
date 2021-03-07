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
import se.jocke.department.entity.Employee;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {
    @Mock
    private EmployeeDao employeeDao;
    @InjectMocks
    EmployeeService systemUnderTest = new EmployeeServiceImpl();

    @BeforeEach
    public void setUp(){
    }

    @Test
    public void findById(){
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("firstName1")
                .lastName("lastName1")
                .fullTime(Boolean.TRUE)
                .salary(BigDecimal.valueOf(25000.0))
                .departmentId(1)
                .build()));

        Employee employee = systemUnderTest.getEmployeeById(1);
        Assertions.assertAll(
                ()->Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                ()->Assertions.assertEquals("firstName1", employee.getFirstName()),
                ()->Assertions.assertEquals("lastName1", employee.getLastName()),
                ()->Assertions.assertEquals(Boolean.TRUE, employee.getFullTime()),
                ()->Assertions.assertEquals(BigDecimal.valueOf(25000.0), employee.getSalary()),
                ()->Assertions.assertEquals(1, employee.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(1);
    }

    @Test
    public void getAllEmployees(){
        when(employeeDao.findAll()).thenReturn(Arrays.asList(EmployeeDatabaseEntry.builder()
                .firstName("Testar")
                .lastName("Testarsson")
                .departmentId(1)
                .fullTime(Boolean.TRUE)
                .salary(BigDecimal.valueOf(25000))
                .employeeId(1)
                .build()));
        List<Employee> employees = systemUnderTest.getAllEmployees();
        Assertions.assertAll(
                ()->assertNotNull(employees),
                ()->assertEquals(1,employees.size())
        );
    }
}
