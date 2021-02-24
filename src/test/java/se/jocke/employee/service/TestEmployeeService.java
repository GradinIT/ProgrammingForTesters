package se.jocke.employee.service;

/* MALL
* @ExtendWith(MockitoExtension.class)
public class TestDepartmentService {
    @Mock
    private DepartmentDao departmentDao;
    @InjectMocks
    private DepartmentService systemUnderTest = new DepartmentServiceImpl();
*/

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

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {
    @Mock
    private EmployeeDao employeeDao;
    @InjectMocks
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();

    /* MALL
    *  @BeforeEach
    public void setUp() {
        when(departmentDao.findById(any(Integer.class))).thenReturn(Optional.of(DepartmentDatabaseEntry.builder()
                .departmentId(1)
                .departmentName("Development")
                .build()));
    }*/

    @BeforeEach                               //My try of method for EmployeeTesting
    public void setUp() {
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder())
                .employeeId()                   // RED, WHY????
                .firstName("firstName1")
                .fullTime()
                .lastName()
                .salary()
                .departmentId()
                .build());
    }

    /* MALL
    *  @Test
    public void findById() {
        Department department = systemUnderTest.getDepartmentById(1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, department.getDepartmentId()),
                () -> Assertions.assertEquals("Development", department.getDepartmentName())
        );
        verify(departmentDao, times(1)).findById(1);
    }*/

    @Test
    public void findById() {
        Employee employee = systemUnderTest.getEmployeeById(1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employee.getEmployeeId()),
                () -> Assertions.assertEquals("firstName1",employee.getFirstName())
        );
    }

}
