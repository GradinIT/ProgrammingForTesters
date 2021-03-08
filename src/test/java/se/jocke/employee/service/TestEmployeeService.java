package se.jocke.employee.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.*;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.service.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {

    private Employee testEmployee;
    private Employee tEmployee;
    private EmployeeDatabaseEntry employeeDatabaseEntry;

    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private final EmployeeService systemUnderTest = new EmployeeServiceImpl();

    @BeforeEach
    void setUp() {
        tEmployee = null;
        testEmployee = EmployeeTestBuilder.builder().build();
        employeeDatabaseEntry = EmployeeDatabaseEntry.builder()
                .employeeId(testEmployee.getEmployeeId().getId())
                .firstName(testEmployee.getFirstName())
                .lastName(testEmployee.getLastName())
                .salary(testEmployee.getSalary())
                .fullTime(testEmployee.getFullTime())
                .departmentId(testEmployee.getDepartmentId())
                .build();
    }

    @Test
    public void testFindEmployee() {
        when(employeeDao.findById(anyInt())).thenReturn(Optional.of(employeeDatabaseEntry));

        tEmployee = systemUnderTest.getEmployeeById(testEmployee.getEmployeeId().getId());
        Assertions.assertAll(
                () -> assertEquals(testEmployee.getEmployeeId().getId(), tEmployee.getEmployeeId().getId()),
                () -> assertEquals(testEmployee.getFirstName(), tEmployee.getFirstName()),
                () -> assertEquals(testEmployee.getLastName(), tEmployee.getLastName()),
                () -> assertEquals(testEmployee.getSalary(), tEmployee.getSalary()),
                () -> assertEquals(testEmployee.getFullTime(), tEmployee.getFullTime()),
                () -> assertEquals(testEmployee.getDepartmentId(), tEmployee.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(testEmployee.getEmployeeId().getId());
    }

    @Test
    public void testCreateEmployee() {
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(employeeDatabaseEntry);

        tEmployee = systemUnderTest.createOrUpdateEmployee(testEmployee);
        Assertions.assertAll(
                () -> assertEquals(testEmployee.getEmployeeId().getId(), tEmployee.getEmployeeId().getId()),
                () -> assertEquals(testEmployee.getFirstName(), tEmployee.getFirstName()),
                () -> assertEquals(testEmployee.getLastName(), tEmployee.getLastName()),
                () -> assertEquals(testEmployee.getSalary(), tEmployee.getSalary()),
                () -> assertEquals(testEmployee.getFullTime(), tEmployee.getFullTime()),
                () -> assertEquals(testEmployee.getDepartmentId(), tEmployee.getDepartmentId())
        );
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void testRemoveEmployee() {
        systemUnderTest.removeEmployee(testEmployee);
        verify(employeeDao, times(1)).delete(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void testUpdateEmployee() {
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(employeeDatabaseEntry);

        tEmployee = systemUnderTest.updateEmployee(testEmployee);

        Assertions.assertAll(
                () -> assertEquals(testEmployee.getEmployeeId().getId(), tEmployee.getEmployeeId().getId()),
                () -> assertEquals(testEmployee.getFirstName(), tEmployee.getFirstName()),
                () -> assertEquals(testEmployee.getLastName(), tEmployee.getLastName()),
                () -> assertEquals(testEmployee.getSalary(), tEmployee.getSalary()),
                () -> assertEquals(testEmployee.getFullTime(), tEmployee.getFullTime()),
                () -> assertEquals(testEmployee.getDepartmentId(), tEmployee.getDepartmentId())
        );
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void testGetAllEmployees() {
        List<EmployeeDatabaseEntry> employees = new ArrayList<>();
        employees.add(employeeDatabaseEntry);
        employees.add(employeeDatabaseEntry);
        employees.add(employeeDatabaseEntry);

        when(employeeDao.findAll()).thenReturn(employees);

        List<Employee> employeeList = systemUnderTest.getAllEmployees();

        assertEquals(3, employeeList.size());

        verify(employeeDao, times(1)).findAll();
    }
}





























 /*   @ExtendWith(MockitoExtension.class)
    public class TestEmployeeService {
        @Mock
        private EmployeeDao employeeDaoDao;
        @InjectMocks
        private EmployeeService systemUnderTest = new EmployeeServiceImpl();

        @BeforeEach
        public void setUp() {
            when(employeeDaoDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                    .employeeId(1)
                    .firstName("firstName")
                    .lastName("lastName")
                    .fullTime(Boolean.TRUE)
                    .salary(BigDecimal.valueOf(25000.00))
                    .departmentId(1)
                    .build()));
        }

        @Test
        public void findById() {
            Employee employee = systemUnderTest.getEmployeeById(1);
            Assertions.assertAll(
                    () -> assertEquals(1, employee.getEmployeeId().getId()),
                    () -> assertEquals("firstName", employee.getFirstName()),
                    () -> assertEquals("lastName", employee.getLastName()),
                    () -> assertEquals(Boolean.TRUE, employee.getFullTime()),
                    () -> assertEquals(BigDecimal.valueOf(25000.00), employee.getSalary()),
                    () -> assertEquals(1, employee.getDepartmentId())
            );
            verify(employeeDaoDao, times(1)).findById(1);
        }
        @Test
        public void testRemoveEmployee() {



            Employee removeEmployee = systemUnderTest.removeEmployee();
            verify(employeeDaoDao, times(1)).delete(any(EmployeeDatabaseEntry.class));

        }

        @Test
        public void testUpdateEmployee() {


            //tempEmp = SYSTEM_UNDER_TEST.updateEmployee(testEmp);
            Employee employee = systemUnderTest.updateEmployee();//getEmployeeById(1);
            Assertions.assertAll(
                    () -> assertEquals(1, employee.getEmployeeId().getId()),
                    () -> assertEquals("firstName", employee.getFirstName()),
                    () -> assertEquals("lastName", employee.getLastName()),
                    () -> assertEquals(Boolean.TRUE, employee.getFullTime()),
                    () -> assertEquals(BigDecimal.valueOf(25000.00), employee.getSalary()),
                    () -> assertEquals(1, employee.getDepartmentId())

                 /*   () -> assertEquals(testEmp.getEmployeeId().getId(), tempEmp.getEmployeeId().getId()),
                    () -> assertEquals(testEmp.getFirstName(), tempEmp.getFirstName()),
                    () -> assertEquals(testEmp.getLastName(), tempEmp.getLastName()),
                    () -> assertEquals(testEmp.getSalary(), tempEmp.getSalary()),
                    () -> assertEquals(testEmp.getFullTime(), tempEmp.getFullTime()),
                    () -> assertEquals(testEmp.getDepartmentId(), tempEmp.getDepartmentId()) */
/*       );
            verify(employeeDaoDao, times(1)).save(any(EmployeeDatabaseEntry.class));
        }

        @Test
        public void testGetAllEmployees() {
            List<EmployeeDatabaseEntry> employees = new ArrayList<>();
            employees.add(empDbE);
            employees.add(empDbE);
            employees.add(empDbE);

            when(employeeDao.findAll()).thenReturn(employees);

            List<Employee> tempEmpList = SYSTEM_UNDER_TEST.getAllEmployees();

            assertEquals(3, tempEmpList.size());

            verify(employeeDao, times(1)).findAll();
        }

    }

*/


    /*import org.junit.jupiter.api.Assertions;
        import org.junit.jupiter.api.BeforeEach;
        import org.junit.jupiter.api.Test;
        import org.junit.jupiter.api.extension.ExtendWith;
        import org.mockito.InjectMocks;
        import org.mockito.Mock;
        import org.mockito.junit.jupiter.MockitoExtension;
        import se.jocke.dao.EmployeeDao;
        import se.jocke.dao.EmployeeDatabaseEntry;
        import se.jocke.department.entity.Employee;
        import se.jocke.employee.builder.EmployeeTestBuilder;
        import se.jocke.service.EmployeeService;
        import se.jocke.service.EmployeeServiceImpl;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Optional;

        import static org.junit.jupiter.api.Assertions.assertEquals;
        import static org.mockito.ArgumentMatchers.anyInt;
        import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {

    private Employee testEmp;
    private Employee tempEmp;
    private EmployeeDatabaseEntry empDbE;

    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private final EmployeeService SYSTEM_UNDER_TEST = new EmployeeServiceImpl();

    @BeforeEach
    void setUp() {
        tempEmp = null;
        testEmp = EmployeeTestBuilder.builder().build();
        empDbE = EmployeeDatabaseEntry.builder()
                .employeeId(testEmp.getEmployeeId().getId())
                .firstName(testEmp.getFirstName())
                .lastName(testEmp.getLastName())
                .salary(testEmp.getSalary())
                .fullTime(testEmp.getFullTime())
                .departmentId(testEmp.getDepartmentId())
                .build();
    }

    @Test
    public void testFindEmployee() {
        when(employeeDao.findById(anyInt())).thenReturn(Optional.of(empDbE));

        tempEmp = SYSTEM_UNDER_TEST.getEmployeeById(testEmp.getEmployeeId().getId());
        Assertions.assertAll(
                () -> assertEquals(testEmp.getEmployeeId().getId(), tempEmp.getEmployeeId().getId()),
                () -> assertEquals(testEmp.getFirstName(), tempEmp.getFirstName()),
                () -> assertEquals(testEmp.getLastName(), tempEmp.getLastName()),
                () -> assertEquals(testEmp.getSalary(), tempEmp.getSalary()),
                () -> assertEquals(testEmp.getFullTime(), tempEmp.getFullTime()),
                () -> assertEquals(testEmp.getDepartmentId(), tempEmp.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(testEmp.getEmployeeId().getId());
    }

    @Test
    public void testCreateEmployee() {
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(empDbE);

        tempEmp = SYSTEM_UNDER_TEST.createOrUpdateEmployee(testEmp);
        Assertions.assertAll(
                () -> assertEquals(testEmp.getEmployeeId().getId(), tempEmp.getEmployeeId().getId()),
                () -> assertEquals(testEmp.getFirstName(), tempEmp.getFirstName()),
                () -> assertEquals(testEmp.getLastName(), tempEmp.getLastName()),
                () -> assertEquals(testEmp.getSalary(), tempEmp.getSalary()),
                () -> assertEquals(testEmp.getFullTime(), tempEmp.getFullTime()),
                () -> assertEquals(testEmp.getDepartmentId(), tempEmp.getDepartmentId())
        );
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void testRemoveEmployee() {
        SYSTEM_UNDER_TEST.removeEmployee(testEmp);
        verify(employeeDao, times(1)).delete(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void testUpdateEmployee() {
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(empDbE);

        tempEmp = SYSTEM_UNDER_TEST.updateEmployee(testEmp);

        Assertions.assertAll(
                () -> assertEquals(testEmp.getEmployeeId().getId(), tempEmp.getEmployeeId().getId()),
                () -> assertEquals(testEmp.getFirstName(), tempEmp.getFirstName()),
                () -> assertEquals(testEmp.getLastName(), tempEmp.getLastName()),
                () -> assertEquals(testEmp.getSalary(), tempEmp.getSalary()),
                () -> assertEquals(testEmp.getFullTime(), tempEmp.getFullTime()),
                () -> assertEquals(testEmp.getDepartmentId(), tempEmp.getDepartmentId())
        );
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void testGetAllEmployees() {
        List<EmployeeDatabaseEntry> employees = new ArrayList<>();
        employees.add(empDbE);
        employees.add(empDbE);
        employees.add(empDbE);

        when(employeeDao.findAll()).thenReturn(employees);

        List<Employee> tempEmpList = SYSTEM_UNDER_TEST.getAllEmployees();

        Assertions.assertEquals(3, tempEmpList.size());

        verify(employeeDao, times(1)).findAll();
    }
}
*/