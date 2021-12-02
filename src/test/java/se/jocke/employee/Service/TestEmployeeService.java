package se.jocke.employee.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.common.dao.EntityAlreadyInStorageException;
import se.jocke.common.dao.EntityNotFoundException;
import se.jocke.employee.Builder.EmployeeDatabaseEntryTestBuilder;
import se.jocke.employee.Builder.EmployeeTestBuilder;
import se.jocke.employee.dao.EmployeeDao;
import se.jocke.employee.dao.EmployeeDatabaseEntry;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.service.EmployeeService;
import se.jocke.employee.service.EmployeeServiceImpl;

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
    public void testFindById() {
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

        Employee employee = systemBeingTested.createEmployee(EMPLOYEE);
        Assertions.assertAll(
                () -> Assertions.assertEquals(employee, EMPLOYEE)

        );
        verify(employeeDao, times(1)).findById(EMPLOYEE.getEmployeeId().getId());
    }

    @Test
    public void testingCreateEmployee() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.empty());
        //when(employeeDao.save(EMPLOYEE_DATABASE_ENTRY)).thenReturn(EMPLOYEE);
        Employee createEmployee = systemBeingTested.createEmployee(EMPLOYEE);
        Assertions.assertAll(
                () -> assertNotNull(createEmployee),
                () -> assertEquals(EMPLOYEE.getEmployeeId(), createEmployee.getEmployeeId()),
                () -> assertEquals(EMPLOYEE.getFirstName(), createEmployee.getFirstName())
        );
    }

    @Test
    public void testEntityNotFoundException() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class,()-> systemBeingTested.getEmployeeById(EMPLOYEE.getEmployeeId().getId()));
    }
    @Test
    public void EntityAlreadyInStorageException() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.of(EMPLOYEE_DATABASE_ENTRY));
        Assertions.assertThrows(EntityAlreadyInStorageException.class,() -> systemBeingTested.createEmployee(EMPLOYEE));
    }
}