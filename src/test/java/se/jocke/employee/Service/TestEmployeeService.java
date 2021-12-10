package se.jocke.employee.Service;

import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.event.SourceFilteringListener;
import se.jocke.common.dao.EntityAlreadyInStorageException;
import se.jocke.common.dao.EntityNotFoundException;
import se.jocke.employee.Builder.EmployeeDatabaseEntryTestBuilder;
import se.jocke.employee.Builder.EmployeeTestBuilder;
import se.jocke.employee.dao.EmployeeDao;
import se.jocke.employee.dao.EmployeeDatabaseEntry;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.entity.EmployeeID;
import se.jocke.employee.service.EmployeeService;
import se.jocke.employee.service.EmployeeServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TestEmployeeService {

    private static final Employee EMPLOYEE = EmployeeTestBuilder.build();
    private static final EmployeeDatabaseEntry EMPLOYEE_DATABASE_ENTRY = EmployeeDatabaseEntryTestBuilder.build();

    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeService systemBeingTested = new EmployeeServiceImpl();


    @Test
    public void testEmployeeFindById() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.of(EMPLOYEE_DATABASE_ENTRY));

        Employee employee = systemBeingTested.getEmployeeById(EMPLOYEE.getEmployeeId().getId());
        Assertions.assertAll(
                () -> Assertions.assertNotNull(employee),
                () -> Assertions.assertEquals(EMPLOYEE, employee)

        );
        verify(employeeDao, times(1)).findById(EMPLOYEE.getEmployeeId().getId());
    }

    @Test
    public void testCreateEmployee() { //Test not working //
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.empty());
        when(employeeDao.save(EMPLOYEE_DATABASE_ENTRY)).thenReturn((EMPLOYEE_DATABASE_ENTRY));
        Employee employee = systemBeingTested.createEmployee(EMPLOYEE);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(employee),
                () -> Assertions.assertEquals(EMPLOYEE, employee)

        );
        verify(employeeDao, times(1)).findById(EMPLOYEE.getEmployeeId().getId());
    }

   @Test
    public void testRemoveEmployee() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.of(EMPLOYEE_DATABASE_ENTRY));
        doNothing().when(employeeDao).delete(EMPLOYEE_DATABASE_ENTRY);

        Employee employee = systemBeingTested.removeEmployee(EMPLOYEE);
        Assertions.assertAll   (
                () -> Assertions.assertNotNull(employee),
                () -> Assertions.assertEquals(EMPLOYEE, employee));

        verify(employeeDao,times(1)).delete(EMPLOYEE_DATABASE_ENTRY);
}


    @Test
    public void testUpdateEmployee() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.of(EMPLOYEE_DATABASE_ENTRY));
        when(employeeDao.save(EMPLOYEE_DATABASE_ENTRY)).thenReturn((EMPLOYEE_DATABASE_ENTRY));
        Employee employee = systemBeingTested.updateEmployee(EMPLOYEE);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(employee),
                () -> Assertions.assertEquals(EMPLOYEE, employee));

        verify(employeeDao, times(1)).save(EMPLOYEE_DATABASE_ENTRY);
    }

    @Test
    public void testGetAllEmployees() {
        when(employeeDao.findAll()).thenReturn(Arrays.asList(EMPLOYEE_DATABASE_ENTRY));

        List<Employee> employees = systemBeingTested.getAllEmployees();

        Assertions.assertAll(
                () -> Assertions.assertNotNull(employees),
                () -> Assertions.assertEquals(1,employees.size()),
                () -> Assertions.assertTrue(employees.contains(EMPLOYEE))
        );

        verify(employeeDao, times(1)).findAll();

    }
    @Test
    public void testEmployeeFindByIDEntityNotFoundException() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> systemBeingTested.getEmployeeById(EMPLOYEE.getEmployeeId().getId()));

    verify(employeeDao, times(1)).findById(EMPLOYEE.getEmployeeId().getId());

    }

    @Test
    public void testCreateEmployeeEntityAlreadyInStorageException() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.of(EMPLOYEE_DATABASE_ENTRY));
        Assertions.assertThrows(EntityAlreadyInStorageException.class, () -> systemBeingTested.createEmployee(EMPLOYEE));

        verify(employeeDao, times(1)).findById(EMPLOYEE.getEmployeeId().getId());

    }

    @Test
    public void testRemoveEmployeeEntityNotFoundException() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> systemBeingTested.removeEmployee(EMPLOYEE));

        verify(employeeDao, times(1)).findById(EMPLOYEE.getEmployeeId().getId());

    }

    @Test
    public void testUpdateEmployeeEntityNotFoundException() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> systemBeingTested.updateEmployee(EMPLOYEE));

        verify(employeeDao, times(1)).findById(EMPLOYEE.getEmployeeId().getId());

    }
}