package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.common.dao.EntityAlreadyInStorageException;
import se.jocke.common.dao.EntityNotFoundException;
import se.jocke.department.dao.DepartmentDao;
import se.jocke.department.dao.DepartmentDatabaseEntry;
import se.jocke.department.entity.Department;
import se.jocke.department.service.DepartmentService;
import se.jocke.department.service.DepartmentServiceImpl;
import se.jocke.department.test.builder.DepartmentDatabaseEntryTestBuilder;
import se.jocke.department.test.builder.DepartmentTestBuilder;
import se.jocke.employee.builder.EmployeeDatabaseEntryTestBuilder;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.employee.dao.EmployeeDao;
import se.jocke.employee.dao.EmployeeDatabaseEntry;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.service.EmployeeService;
import se.jocke.employee.service.EmployeeServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TestEmployeeService {

    private static final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();
    private static final EmployeeDatabaseEntry EMPLOYEE_DATABASE_ENTRY = EmployeeDatabaseEntryTestBuilder.build();

    // EMPLOYEE_DATABASE_ENTRY får sitt värde via EmployeeTestFixture
    //EmployeeDatabaseEntry(employeeId=2, firstName=Test/QA, lastName=SQUARE1, salary=50000, fullTime=true, departmentId=5)


    @Mock
    private EmployeeDao employeeDao;

    @InjectMocks
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();

    @Test
    // GetEmployeeById,
    // Happy flow: assert and verify getEmployeeById.
    public void testGetEmployeeById() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.of(EMPLOYEE_DATABASE_ENTRY));
        Employee employee = systemUnderTest.getEmployeeById(EMPLOYEE.getEmployeeId().getId());
        Assertions.assertAll(
                () -> Assertions.assertNotNull(employee), // check that present
                () -> Assertions.assertEquals(EMPLOYEE, employee)
        );
        verify(employeeDao, times(1)).findById(EMPLOYEE.getEmployeeId().getId());
    }

    @Test
    // GetEmployeeById,
    // Error handling flow: Assert that EntityNotFoundException is thrown if no employeeentity found
    public void testGetEmployeeByIdEntityNotFoundException() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.empty()); //
        Assertions.assertThrows(EntityNotFoundException.class, () -> systemUnderTest.getEmployeeById(EMPLOYEE.getEmployeeId().getId()));
    }
    @Test
    // createEmployee,
    // Happy flow: Assert that new employee is created and saved
    //  en when-regel för .findById som hårdkodar tom retur
    //  en when-regel när .save och return
    public void testCreateEmployee() {
                when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.empty()); //obs thenReturn hårdkodat empty
        when(employeeDao.save(EMPLOYEE_DATABASE_ENTRY)).thenReturn(EMPLOYEE_DATABASE_ENTRY);
        Employee employee = systemUnderTest.createEmployee(EMPLOYEE);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(employee),
                () -> Assertions.assertEquals(EMPLOYEE, employee)
        );
    }

    @Test
    //  createEmployee,
    // Error handling flow: Assert that EntityAlreadyInStorageException if employee already exists
    public void testCreateEmployeeEntityAlreadyInStorageException() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.of(EMPLOYEE_DATABASE_ENTRY));
        Assertions.assertThrows(EntityAlreadyInStorageException.class, () -> systemUnderTest.createEmployee(EMPLOYEE));
    }

    @Test
    public void testUpdateEmployee(){
//Update employee -
// Happy flow: updateEmployee(EMPLOYEE)
        //  en when-regel för .findById som hårdkodar retur av en employee
        //  en when-regel för .save av det som sparas och returneras till databasen
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.of(EMPLOYEE_DATABASE_ENTRY)); // we find and return employee by employeeId
        when(employeeDao.save(EMPLOYEE_DATABASE_ENTRY)).thenReturn(EMPLOYEE_DATABASE_ENTRY); //  save and return EMPLOYEE_DATABASE_ENTRY
        Employee employee = systemUnderTest.updateEmployee(EMPLOYEE); //
        System.out.println("employee: " + employee);
        System.out.println("EMPLOYEE " +EMPLOYEE);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(employee),
                () -> Assertions.assertEquals(EMPLOYEE, employee)
        );
    }

    @Test
    public void TODO_testUpdateEmployeeEntityNotFound(){
        // Update employee -
        // Error handling EntityNotFound if no employee present to do the update on
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.empty()); //thenReturn hårdkodat empty
        Assertions.assertThrows(EntityNotFoundException.class, () -> systemUnderTest.updateEmployee(EMPLOYEE));
    }

    @Test
    public void testRemoveEmployee() {
        // removeEmployee,
        // Happy flow: Remove Employee, verify 1 time OK
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.of(EMPLOYEE_DATABASE_ENTRY));
        doNothing().when(employeeDao).delete(EMPLOYEE_DATABASE_ENTRY); //doNothing() ???

        Employee employee = systemUnderTest.removeEmployee(EMPLOYEE);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(employee),
                () -> Assertions.assertEquals(EMPLOYEE, employee)
        );
        verify(employeeDao,times(1)).delete(EMPLOYEE_DATABASE_ENTRY);
    }

    @Test
    public void testRemoveEmployeeEntityNotFound() {
        //removeEmployee,
        // Error handling flow: Throw EntityNotFoundException if no employeeId found in removeEmployee
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> systemUnderTest.removeEmployee(EMPLOYEE));
    }


    @Test
    public void testGetAllEmployees() {
        //Happy flow: getAllEmployees
        when(employeeDao.findAll()).thenReturn(Arrays.asList(EMPLOYEE_DATABASE_ENTRY));
        List<Employee> employeeList = systemUnderTest.getAllEmployees();
        Assertions.assertAll(
                () -> Assertions.assertNotNull(employeeList),
                () -> Assertions.assertEquals(1, employeeList.size()),
                () -> Assertions.assertTrue(employeeList.contains(EMPLOYEE))
        );
    }
}
