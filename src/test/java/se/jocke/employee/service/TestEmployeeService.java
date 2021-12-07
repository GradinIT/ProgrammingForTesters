package se.jocke.employee.service;

import liquibase.pro.packaged.O;
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
import se.jocke.employee.dao.EmployeeDao;
import se.jocke.employee.dao.EmployeeDatabaseEntry;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.test.builder.EmployeeDatabaseEntryTestBuilder;
import se.jocke.employee.test.builder.EmployeeTestBuilder;

import java.util.Optional;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TestEmployeeService {

    private static final Employee EMPLOYEE = EmployeeTestBuilder.bygg();
    private static final EmployeeDatabaseEntry EMPLOYEE_DATABASE_ENTRY = EmployeeDatabaseEntryTestBuilder.bygg();

    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();

    @Test
    public void testGetEmployeeById() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.of(EMPLOYEE_DATABASE_ENTRY));
        Employee employee = systemUnderTest.getEmployeeById(EMPLOYEE.getEmployeeId().getId());
        Assertions.assertAll(
                ()-> Assertions.assertNotNull(employee),
                ()-> Assertions.assertEquals(EMPLOYEE, employee)
        );
        verify(employeeDao, times(1)).findById(EMPLOYEE.getEmployeeId().getId());
    }

    @Test
    public void testGetEmployeeByIdEntityNotFoundException() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, ()-> systemUnderTest.getEmployeeById(EMPLOYEE.getEmployeeId().getId()));
    }
    @Test
    public void testCreateEmployee() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.empty());
        when(employeeDao.save(EMPLOYEE_DATABASE_ENTRY)).thenReturn(EMPLOYEE_DATABASE_ENTRY);
        Employee employee = systemUnderTest.createEmployee(EMPLOYEE);
        Assertions.assertAll(
                ()-> Assertions.assertNotNull(employee),
                ()-> Assertions.assertEquals(EMPLOYEE, employee)
        );
    }

    @Test
    public void testCreateEmployeeEntityAlreadyInStorageException() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.of(EMPLOYEE_DATABASE_ENTRY));
        Assertions.assertThrows(EntityAlreadyInStorageException.class, ()-> systemUnderTest.createEmployee(EMPLOYEE));
    }


}
