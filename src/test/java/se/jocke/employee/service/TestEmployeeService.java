package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.api.EmployeeModel;
import se.jocke.dao.DepartmentDatabaseEntry;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.department.entity.Department;
import se.jocke.department.entity.Employee;
import se.jocke.department.entity.EmployeeID;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.service.DepartmentService;
import se.jocke.service.DepartmentServiceImpl;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {

    @Mock
    private EmployeeDao employeeDao;
    @InjectMocks
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();

    @BeforeEach
    public void setUp() {

        List<EmployeeDatabaseEntry> empDBList = new ArrayList<>();
        int empId=50;
        empDBList.add(EmployeeDatabaseEntry.builder()
                .employeeId(empId++)
                .firstName("david")
                .lastName("david")
                .salary(BigDecimal.valueOf(44))
                .fullTime(true)
                .departmentId(4)
                .build());
        empDBList.add(EmployeeDatabaseEntry.builder()
                .employeeId(empId++)
                .firstName("smith")
                .lastName("smith")
                .salary(BigDecimal.valueOf(44))
                .fullTime(true)
                .departmentId(4)
                .build());
        empDBList.add(EmployeeDatabaseEntry.builder()
                .employeeId(empId++)
                .firstName("mr")
                .lastName("kennysson")
                .salary(BigDecimal.valueOf(44))
                .fullTime(true)
                .departmentId(4)
                .build());

        lenient().when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .firstName("fred")
                .lastName("lind")
                .salary(BigDecimal.valueOf(44))
                .fullTime(true)
                .departmentId(4)
                .build()));

        lenient().when(employeeDao.save(any())).thenReturn((EmployeeDatabaseEntry.builder()
                .employeeId(6)
                .firstName("david")
                .lastName("david")
                .salary(BigDecimal.valueOf(44))
                .fullTime(true)
                .departmentId(4)
                .build()));

        lenient().when(employeeDao.findAll()).thenReturn(empDBList);

    }

    @Test
    public void findById() {
        Employee employee = systemUnderTest.getEmployeeById(1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("fred", employee.getFirstName()),
                () -> Assertions.assertEquals("lind", employee.getLastName()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(44), employee.getSalary()),
                () -> Assertions.assertEquals(true, employee.getFullTime()),
                () -> Assertions.assertEquals(4, employee.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(1);
    }

    @Test
    public void createOrUpdateEmployeeByUnit() {
        Employee employee = EmployeeTestBuilder.builder().build();

        Employee returnEmp= systemUnderTest.createOrUpdateEmployee(employee);
        Assertions.assertAll(
                () -> Assertions.assertEquals(6, returnEmp.getEmployeeId().getId()),
                () -> Assertions.assertEquals("david", returnEmp.getFirstName()),
                () -> Assertions.assertEquals("david", returnEmp.getLastName()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(44), returnEmp.getSalary()),
                () -> Assertions.assertEquals(true, returnEmp.getFullTime()),
                () -> Assertions.assertEquals(4, returnEmp.getDepartmentId()));
    }

    @Test
    public void deleteEmployee() {
        Employee employee = EmployeeTestBuilder.builder().build();

        Employee returnEmp= systemUnderTest.removeEmployee(employee);
        Assertions.assertAll(
                () -> Assertions.assertEquals(employee.getEmployeeId().getId(), returnEmp.getEmployeeId().getId()),
                () -> Assertions.assertEquals(employee.getFirstName(), returnEmp.getFirstName()),
                () -> Assertions.assertEquals(employee.getLastName(), returnEmp.getLastName()),
                () -> Assertions.assertEquals(employee.getSalary(), returnEmp.getSalary()),
                () -> Assertions.assertEquals(employee.getFullTime(), returnEmp.getFullTime()),
                () -> Assertions.assertEquals(employee.getDepartmentId(), returnEmp.getDepartmentId()));
    }


    @Test
    public void updateEmployeeByUnit() {
        Employee employee = EmployeeTestBuilder.builder().build();

        Employee returnEmp= systemUnderTest.updateEmployee(employee);
        Assertions.assertAll(
                () -> Assertions.assertEquals(6, returnEmp.getEmployeeId().getId()),
                () -> Assertions.assertEquals("david", returnEmp.getFirstName()),
                () -> Assertions.assertEquals("david", returnEmp.getLastName()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(44), returnEmp.getSalary()),
                () -> Assertions.assertEquals(true, returnEmp.getFullTime()),
                () -> Assertions.assertEquals(4, returnEmp.getDepartmentId()));
    }

    @Test
    public void getAllEmployeesUnit() {
        List<Employee> employeeList = systemUnderTest.getAllEmployees();
        Assertions.assertAll(
                () -> Assertions.assertEquals(3, employeeList.size()),
                () -> Assertions.assertEquals(52, employeeList.get(2).getEmployeeId().getId()),
                () -> Assertions.assertEquals("mr", employeeList.get(2).getFirstName()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(44).setScale(2), employeeList.get(1).getSalary().setScale(2)));
    }

}
/*

    Employee createOrUpdateEmployee(Employee employee); ok

    Employee removeEmployee(Employee employee); ok

    Employee updateEmployee(Employee employee); ok

    List<Employee> getAllEmployees(); ok
*/
