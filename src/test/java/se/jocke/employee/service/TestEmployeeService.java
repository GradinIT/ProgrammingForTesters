package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import static org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.dao.EntityNotFoundException;
import se.jocke.dao.mapper.EmployeePojoMapper;
import se.jocke.department.entity.Employee;
import se.jocke.department.entity.EmployeeID;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
public class TestEmployeeService {
    @Mock // Vi har en mockad dao för att vi vill isolera funktionaliteten i Service-klassen och BARA testa metoderna där
    private EmployeeDao employeeDao;
    @InjectMocks // Vi injecerar vår mockade dao i vårt service-objekt
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();

    Employee exampleEmployee;
    EmployeeDatabaseEntry employeeDbEntry;

    @BeforeAll
    public void setUp() {
        // initierar en exempel-employee att jämföra mot i assertions
        exampleEmployee = EmployeeTestBuilder.builderMethod().build();

        // initierar ett database entry baserad på exempel-employeeen
        employeeDbEntry = EmployeeDatabaseEntry.builder()
                .employeeId(exampleEmployee.getEmployeeId().getId())
                .firstName(exampleEmployee.getFirstName())
                .lastName(exampleEmployee.getLastName())
                .salary(exampleEmployee.getSalary())
                .fullTime(exampleEmployee.getFullTime())
                .departmentId(exampleEmployee.getDepartmentId())
                .build();
    }

    @Test
    public void testGetEmployeeById() {

        when(employeeDao.findById(any())).thenReturn(Optional.of(employeeDbEntry));

        Employee foundEmployee = systemUnderTest.getEmployeeById(1);

        assertions(exampleEmployee, foundEmployee);

        verify(employeeDao, times(1)).findById(any());
    }

    @Test
    public void testGetAllEmployees() {

        when(employeeDao.findAll()).thenReturn(Arrays.asList(employeeDbEntry));

        List<Employee> allEmployees = systemUnderTest.getAllEmployees();

        assertEquals(1, allEmployees.size());

        verify(employeeDao, times(1)).findAll();

    }

    @Test
    public void testCreateEmployee() {

        when(employeeDao.findById(any())).thenReturn(Optional.empty());
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(employeeDbEntry);

        Employee addedEmployee = systemUnderTest.createEmployee(exampleEmployee);

        assertions(exampleEmployee, addedEmployee);

        verify(employeeDao, times(1)).findById(any());
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    public void testRemoveEmployee() {
        // Kanske inte ska mockas pga returnerar inget?
        when(employeeDao.findById(any())).thenReturn(Optional.of(employeeDbEntry));
        // Varför behövs ingen regel för delete?

        Employee removedEmployee = systemUnderTest.removeEmployee(exampleEmployee);

        assertions(exampleEmployee, removedEmployee);

        verify(employeeDao, times(1)).findById(any());
        verify(employeeDao, times(1)).delete(any(EmployeeDatabaseEntry.class));

    }

    @Test
    public void testRemoveEmployeeErrorMessage() {
        when(employeeDao.findById(exampleEmployee.getEmployeeId().getId()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> systemUnderTest.removeEmployee(exampleEmployee));

        verify(employeeDao,times(1)).findById(any());
        verify(employeeDao,times(0)).delete(any());
    }

    @Test
    public void testUpdateEmployee() {

        Employee employeeWithNewName = Employee.builder()
                .employeeId(EmployeeID.builder().id(exampleEmployee.getEmployeeId().getId()).build())
                .firstName("Mocke")
                .lastName(exampleEmployee.getLastName())
                .salary(exampleEmployee.getSalary())
                .fullTime(exampleEmployee.getFullTime())
                .departmentId(exampleEmployee.getDepartmentId())
                .build();

        when(employeeDao.findById(any())).thenReturn(Optional.of(EmployeePojoMapper.mapReturnDbEntry(employeeWithNewName)));
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(EmployeePojoMapper.mapReturnDbEntry(employeeWithNewName));

        Employee updatedEmployee = systemUnderTest.updateEmployee(employeeWithNewName);

        assertions(employeeWithNewName, updatedEmployee);

        verify(employeeDao, times(1)).findById(any());
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    public void assertions(Employee expected, Employee actual) {
        assertNotNull(actual);
        assertEquals(expected.getEmployeeId().getId(), actual.getEmployeeId().getId());
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getSalary(), actual.getSalary());
        assertEquals(expected.getFullTime(), actual.getFullTime());
        assertEquals(expected.getDepartmentId(), actual.getDepartmentId());
    }
}
