package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.dao.EntityAlreadyInStorageException;
import se.jocke.department.entity.Employee;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {

    @Mock
    private EmployeeDao employeeDao;
    @InjectMocks
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();

    @BeforeEach
    public void setUp() {
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("sara")
                .lastName("mora")
                .fullTime(true)
                .salary(BigDecimal.valueOf(20000))
                .departmentId(5)

                .build()));
    }

    @Test
    public void findById() {
        Employee employee = systemUnderTest.getEmployeeById(1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("sara", employee.getFirstName()),
                () -> Assertions.assertEquals("mora", employee.getLastName()),
                () -> Assertions.assertEquals(true, employee.getFullTime()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(20000), employee.getSalary()),
                () -> Assertions.assertEquals(5, employee.getDepartmentId())


        );
        verify(employeeDao, times(1)).findById(1);
    }


    @Test
    public void removeEmployee() {

        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("firstName1")
                .lastName("lastName1")
                .fullTime(Boolean.TRUE)
                .salary(BigDecimal.valueOf(25000.00))
                .departmentId(1)
                .build()));

        Employee employee = systemUnderTest.getEmployeeById(1);
        systemUnderTest.removeEmployee(employee);
        verify(employeeDao, times(1)).delete(any(EmployeeDatabaseEntry.class));
    }




}















