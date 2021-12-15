package se.jocke.employee.unittest.service;

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
import se.jocke.department.entity.Department;
import se.jocke.department.test.builder.DepartmentDatabaseEntryTestBuilder;
import se.jocke.employee.dao.EmployeeDao;
import se.jocke.employee.dao.EmployeeDatabaseEntry;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.service.EmployeeService;
import se.jocke.employee.service.EmployeeServiceImpl;
import se.jocke.employee.test.builder.EmployeeDatabaseEntryTestBuilder;
import se.jocke.employee.test.builder.EmployeeTestBuilder;


import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TestEmployeeService {


    private static final Employee EMPLOYEE = EmployeeTestBuilder.build();
    //Private innebär att detta endast kan nås i den klassen den är definierad.
    //Staic innebär att det inte tillhör något objekt i klassen utan själva klassen.
    //Final innebär att värdet på instansen inte kan ändras.
    //Employee EMPLOYEE = EmployeeTestBuilder.build() innebär att vi skapar EMPLOYEE av typen Employee och fyller den med buildern. Stora versaler innebär att det är en hårdkodad kod.
    private static final EmployeeDatabaseEntry EMPLOYEE_DATABASE_ENTRY = EmployeeDatabaseEntryTestBuilder.build();
    // Skapar EMPLOYEE_DATABASE_ENTRY av typen EmployeeDatabaseEntry och tilldelar den buildern
    @Mock
    //"vad"
    private EmployeeDao employeeDao;
    // Skapar en fejkad verisom av en extern eller en intern funktion emolyeeDao av typen Employeedao
    @InjectMocks
    // "var"
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();

    // Tar beteendet från "new EmployeeServiceiml som är intefacet och lägger in det i systemUnderTest. Skapar ett objekt.
    @Test
    //"hur"
    public void testFindById() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.of(EMPLOYEE_DATABASE_ENTRY));
        //When är regelverket. when(object.method(any(ParamType))).thenReturn( return value)
        // Vi använder EmployeeDao och söker efter ID och EMPLOYEE.
        Employee employee = systemUnderTest.getEmployeeById(EMPLOYEE.getEmployeeId().getId());
        // Vi skapar en employee av typen Employee och hämtar EMPLOYEEs id?

        Assertions.assertAll(
                () -> Assertions.assertNotNull(employee),
                //Lambdauttryck. Testar så att employee inte är lika med null
                () -> Assertions.assertEquals(EMPLOYEE, employee)
                //Jämför så att EMPLOYEE är samma sak som employee
        );
        verify(employeeDao, times(1)).findById(EMPLOYEE.getEmployeeId().getId());
        // Vi vill säkerställa att den mockade metoden blir kallad 1 gång hittar EMPLOYEE och tar IDt
    }

    @Test
    public void testEntityNotFoundException() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.empty());
        //När vi retunerar tomt (null, KOLLA) så får vi ett exception
        Assertions.assertThrows(EntityNotFoundException.class,
                //
                () -> systemUnderTest.getEmployeeById(EMPLOYEE.getEmployeeId().getId()));

    }

    @Test
    public void testGetEmployeeByIdEntityNotFoundException() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> systemUnderTest.getEmployeeById(EMPLOYEE.getEmployeeId().getId()));
    }

    @Test
    public void testCreateEmployee() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.empty());
        when(employeeDao.save(EMPLOYEE_DATABASE_ENTRY)).thenReturn(EMPLOYEE_DATABASE_ENTRY);
        Employee employee = systemUnderTest.createEmployee(EMPLOYEE);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(employee),
                () -> Assertions.assertEquals(EMPLOYEE, employee)
        );
    }
    @Test
    public void testCreateEmployeeEntityAlreadyInStorageException() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.of(EMPLOYEE_DATABASE_ENTRY));
        Assertions.assertThrows(EntityAlreadyInStorageException.class, () -> systemUnderTest.createEmployee(EMPLOYEE));
    }

    @Test
    public void testRemoveEmployee() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.of(EMPLOYEE_DATABASE_ENTRY));
        doNothing().when(employeeDao).delete(EMPLOYEE_DATABASE_ENTRY);

        Employee employee = systemUnderTest.removeEmployee(EMPLOYEE);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(employee),
                () -> Assertions.assertEquals(EMPLOYEE, employee)
        );
        verify(employeeDao,times(1)).delete(EMPLOYEE_DATABASE_ENTRY);
    }
    @Test
    public void testRemoveEmployeeEntityNotFound() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> systemUnderTest.removeEmployee(EMPLOYEE));
    }
    @Test
    public void testUpdateEmployee() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.of(EMPLOYEE_DATABASE_ENTRY));
        when(employeeDao.save(EMPLOYEE_DATABASE_ENTRY)).thenReturn(EMPLOYEE_DATABASE_ENTRY);
        Employee employee = systemUnderTest.updateEmployee(EMPLOYEE);
        Assertions.assertAll(
                () -> Assertions.assertNotNull(employee),
                () -> Assertions.assertEquals(EMPLOYEE, employee)
        );
        verify(employeeDao,times(1)).save(EMPLOYEE_DATABASE_ENTRY);
    }
    @Test
    public void testUpdateEmployeeEntityNotFound() {
        when(employeeDao.findById(EMPLOYEE.getEmployeeId().getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> systemUnderTest.updateEmployee(EMPLOYEE));
    }
    @Test
    public void testGetAllEmployees() {
        when(employeeDao.findAll()).thenReturn(EmployeeDatabaseEntryTestBuilder.buildList());

        List<Employee> employees = systemUnderTest.getAllEmployees();

        Assertions.assertAll(
                () -> Assertions.assertNotNull(employees),
                () -> Assertions.assertEquals(1, employees.size()),
                () -> Assertions.assertTrue(employees.contains(EMPLOYEE))
        );
    }
}




