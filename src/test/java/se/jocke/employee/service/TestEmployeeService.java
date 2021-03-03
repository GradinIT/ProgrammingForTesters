package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TestEmployeeService {
    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();

    @BeforeEach
    public void setUp() {

        List<EmployeeDatabaseEntry> employerDBList = new ArrayList<EmployeeDatabaseEntry>();
        int nameIndex = 1;
        int salary = 37777;
        for (int i=0; i<3; i++) {
            employerDBList.add(EmployeeDatabaseEntry.builder()
                    .employeeId(6)
                    .firstName("David" + nameIndex)
                    .lastName("Smith" + nameIndex++)
                    .salary(BigDecimal.valueOf((salary++)))
                    .fullTime(true)
                    .departmentId(1000-i)
                    .build());
        }

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
                .firstName("David")
                .lastName("David")
                .salary(BigDecimal.valueOf(44))
                .fullTime(true)
                .departmentId(4)
                .build()));
        lenient().when(employeeDao.findAll()).thenReturn(employerDBList);

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
        Employee returnEmp = systemUnderTest.createOrUpdateEmployee(employee);
        Assertions.assertAll(
                () -> Assertions.assertEquals(6, returnEmp.getEmployeeId().getId()),
                () -> Assertions.assertEquals("David", returnEmp.getFirstName()),
                () -> Assertions.assertEquals("David", returnEmp.getLastName()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(44), returnEmp.getSalary()),
                () -> Assertions.assertEquals(true, returnEmp.getFullTime()),
                () -> Assertions.assertEquals(4, returnEmp.getDepartmentId()));
    }

    @Test
    public void removeEmployeeByUnit() {

        Employee employee = EmployeeTestBuilder.builder().build();
        Employee returnEmp = systemUnderTest.removeEmployee(employee);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(returnEmp),
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
        Employee returnEmp = systemUnderTest.updateEmployee(employee);
        Assertions.assertAll(
                () -> Assertions.assertEquals(6, returnEmp.getEmployeeId().getId()),
                () -> Assertions.assertEquals("David", returnEmp.getFirstName()),
                () -> Assertions.assertEquals("David", returnEmp.getLastName()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(44), returnEmp.getSalary()),
                () -> Assertions.assertEquals(true, returnEmp.getFullTime()),
                () -> Assertions.assertEquals(4, returnEmp.getDepartmentId()));
    }

    @Test
    public void getAllEmployeesUnit() {
        List<Employee> employeeList = systemUnderTest.getAllEmployees();
        Assertions.assertAll(
                () -> Assertions.assertEquals(3, employeeList.size()),
                () -> Assertions.assertEquals("David3", employeeList.get(2).getFirstName()),
                () -> Assertions.assertEquals("Smith1", employeeList.get(0).getLastName()),
                () -> Assertions.assertEquals("Smith1", employeeList.get(0).getLastName()),
                () -> Assertions.assertEquals(1000-1, employeeList.get(1).getDepartmentId()));
    }

}
