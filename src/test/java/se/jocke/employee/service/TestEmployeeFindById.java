package se.jocke.employee.service;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.dao.EntityNotFoundException;
import se.jocke.department.entity.Employee;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeFindById {

    @Mock
    EmployeeDao employeeDao;
    @InjectMocks
    EmployeeService systemUnderTest = new EmployeeServiceImpl();

    @Mock
    EmployeeDatabaseEntry employeeDatabaseEntry;

    @BeforeEach
    public void setUp() {

        employeeDatabaseEntry = employeeDatabaseEntry.builder()
                .firstName("firstName1")
                .lastName("lastName1")
                .departmentId(1)
                .employeeId(1)
                .salary(BigDecimal.valueOf(25000.00))
                .fullTime(true).build();
    }

    @Test
    public void findById() {
        when(employeeDao.findById(1)).thenReturn(Optional.of(employeeDatabaseEntry));
        Employee employee = systemUnderTest.getEmployeeById(1);
        Assertions.assertAll(
                () -> Assertions.assertEquals("firstName1", employee.getFirstName()),
                () -> Assertions.assertEquals("lastName1", employee.getLastName()),
                () -> Assertions.assertEquals(Integer.valueOf(1), employee.getDepartmentId()),
                () -> Assertions.assertEquals(Integer.valueOf(1), employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(25000.00), employee.getSalary()),
                () -> Assertions.assertEquals(true, employee.getFullTime())

        );
        verify(employeeDao, times(1)).findById(1);
    }

    @Test
    public void testEntityNotFoundException() {
        when(employeeDao.findById(999)).thenThrow(EntityNotFoundException.class);
        Assertions.assertThrows(EntityNotFoundException.class, () -> systemUnderTest.getEmployeeById(999));
        verify(employeeDao,times(1)).findById(999);
    }


}
