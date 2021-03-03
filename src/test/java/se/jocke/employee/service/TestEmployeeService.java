package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import se.jocke.dao.DepartmentDao;
import se.jocke.dao.DepartmentDatabaseEntry;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.department.entity.Department;
import se.jocke.department.entity.Employee;
import se.jocke.service.DepartmentService;
import se.jocke.service.DepartmentServiceImpl;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TestEmployeeService {


    @Mock
    private EmployeeDao EmployeeDao;
    @InjectMocks
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();



    @BeforeEach
    public void setUp() {
        when(EmployeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(200)
                .departmentId(1)
                .firstName("Anders")
                .lastName("Andersson")
                .fullTime(Boolean.TRUE)
                .salary(BigDecimal.valueOf(25000))
                .build()));

}


    @Test
    public void findById() {
        Employee employee = systemUnderTest.getEmployeeById(100);
        Assertions.assertAll(
                () -> Assertions.assertEquals(200, employee.getEmployeeId()),
                () -> Assertions.assertEquals(1, employee.getDepartmentId()),
                () -> Assertions.assertEquals("Anders", employee.getFirstName())
        );
        verify(EmployeeDao, times(1)).findById(1);
    }
}