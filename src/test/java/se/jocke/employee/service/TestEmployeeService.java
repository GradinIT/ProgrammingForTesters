package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.common.dao.EntityAlreadyInStorageException;
import se.jocke.common.dao.EntityNotFoundException;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.employee.dao.EmployeeDatabaseEntry;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.dao.EmployeeDao;
import se.jocke.employee.entity.EmployeeID;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TestEmployeeService {
    @Mock
    private EmployeeDao employeeDao;

    private Employee employee;

    @InjectMocks
    private EmployeeService systemUnderTest = new EmployeeServiceImpl();

    @BeforeEach
    public void populateEmployeeObject() {
        employee = EmployeeTestBuilder.builder().build();
    }

    public EmployeeDatabaseEntry employeeDataBaseEntryBuilder(Employee employee) {
        return EmployeeDatabaseEntry.builder()
                .employeeId(employee.getEmployeeId().getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .salary(employee.getSalary())
                .fullTime(employee.getFullTime())
                .departmentId(employee.getDepartmentId())
                .build();
    }

    @Test
    @DisplayName("Test get employee by ID")
    public void getEmployeeById() {
        Integer empID = employee.getEmployeeId().getId();

        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(employeeDataBaseEntryBuilder(employee)));
        Employee employee = systemUnderTest.getEmployeeById(empID);

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, empID),
                () -> Assertions.assertEquals("firstName1", employee.getFirstName()),
                () -> Assertions.assertEquals("LastName1", employee.getLastName()),
                () -> Assertions.assertEquals(new BigDecimal(25000), employee.getSalary()),
                () -> Assertions.assertEquals(true, employee.getFullTime()),
                () -> Assertions.assertEquals(1, employee.getDepartmentId())
        );
        verify(employeeDao, times(1)).findById(empID);
    }

    @Test
    @DisplayName("Test get employee by ID")
    public void getEmployeeByIdNotFound() {
        when(employeeDao.findById(employee.getEmployeeId().getId())).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> systemUnderTest.getEmployeeById(employee.getEmployeeId().getId()));
        verify(employeeDao, times(1)).findById(employee.getEmployeeId().getId());
    }

    @Test
    @DisplayName("Test get all employees")
    public void getAllEmployees() {
        when(employeeDao.findAll()).thenReturn(Arrays.asList(employeeDataBaseEntryBuilder(employee)));

        List<Employee> employees = systemUnderTest.getAllEmployees();
        Assertions.assertAll(
                () -> assertNotNull(employees),
                () -> assertEquals(1, employees.size())
        );
    }

    @Test
    @DisplayName("Test create employee")
    public void createEmployee() {

        Employee emp1 = Employee.builder()
                .employeeId(EmployeeID.builder().id(4).build())
                .firstName("firstname4")
                .lastName("lastname4")
                .salary(new BigDecimal(25000))
                .fullTime(true)
                .departmentId(4)
                .build();

        EmployeeDatabaseEntry newEmployeeDatabaseEntry = employeeDataBaseEntryBuilder(emp1);

        when(employeeDao.save(newEmployeeDatabaseEntry)).thenReturn(newEmployeeDatabaseEntry);
        systemUnderTest.createEmployee(emp1);
        verify(employeeDao, times(1)).save(newEmployeeDatabaseEntry);
    }

    @Test
    @DisplayName("Test remove employee by ID")
    public void removeEmployeeById() {
        when(employeeDao.findById(employee.getEmployeeId().getId())).thenReturn(Optional.of(employeeDataBaseEntryBuilder(employee)));
        systemUnderTest.removeEmployee(employee);
        verify(employeeDao, times(1)).findById(employee.getEmployeeId().getId());
        verify(employeeDao, times(1)).delete(any(EmployeeDatabaseEntry.class));
    }

    @Test
    @DisplayName("Test remove employee - not found")
    public void removeEmployeeNotFound() {
        when(employeeDao.findById(employee.getEmployeeId().getId())).thenReturn(Optional.empty());
        //when(employeeDao.findById(employee.getEmployeeId().getId())).thenReturn(Optional.of(employeeDataBaseEntryBuilder(employee)));
        Assertions.assertThrows(EntityNotFoundException.class, () -> systemUnderTest.removeEmployee(employee));
    }

    @Test
    @DisplayName("Test update employee")
    public void updateEmployee() {
        EmployeeDatabaseEntry employeeDatabaseEntry = employeeDataBaseEntryBuilder(employee);

        when(employeeDao.findById(employee.getEmployeeId().getId())).thenReturn(Optional.of(employeeDatabaseEntry));
        when(employeeDao.save(employeeDatabaseEntry)).thenReturn(employeeDatabaseEntry);
        Employee updateEmployee = systemUnderTest.updateEmployee(employee);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(employee),
                () -> Assertions.assertEquals(employee, updateEmployee));

        verify(employeeDao, times(1)).save(employeeDatabaseEntry);
    }

    @Test
    @DisplayName("Test update employee - not found")
    public void updateEmployeeNotFound() {
        when(employeeDao.findById(employee.getEmployeeId().getId())).thenReturn(Optional.empty());
        //when(employeeDao.findById(employee.getEmployeeId().getId())).thenReturn(Optional.of(employeeDataBaseEntryBuilder(employee)));
        Assertions.assertThrows(EntityNotFoundException.class, () -> systemUnderTest.updateEmployee(employee));
    }

    @Test
    @DisplayName("Test create employee Happy Flow")
    public void createEmployeeHappyFlow() {
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.empty());
        when(employeeDao.save(any(EmployeeDatabaseEntry.class))).thenReturn(employeeDataBaseEntryBuilder(employee));

        Employee createdEmployee = systemUnderTest.createEmployee(employee);

        Assertions.assertAll(
                () -> Assertions.assertNotNull(createdEmployee),
                () -> Assertions.assertEquals(employee.getEmployeeId(), createdEmployee.getEmployeeId()),
                () -> Assertions.assertEquals(employee.getFirstName(), createdEmployee.getFirstName()),
                () -> Assertions.assertEquals(employee.getLastName(), createdEmployee.getLastName()),
                () -> Assertions.assertEquals(employee.getSalary(), createdEmployee.getSalary()),
                () -> Assertions.assertEquals(employee.getFullTime(), createdEmployee.getFullTime()),
                () -> Assertions.assertEquals(employee.getDepartmentId(), createdEmployee.getDepartmentId()));

        verify(employeeDao, times(1)).findById(any(Integer.class));
        verify(employeeDao, times(1)).save(any(EmployeeDatabaseEntry.class));
    }

    @Test
    @DisplayName("Test create employee error")
    public void createEmployeeError() {
        when(employeeDao.findById(employee.getEmployeeId().getId())).thenReturn(Optional.of(employeeDataBaseEntryBuilder(employee)));
        Throwable exception = Assertions.assertThrows(EntityAlreadyInStorageException.class, () -> {
            systemUnderTest.createEmployee(employee);
        });
        Assertions.assertEquals("Entity with id " + employee.getEmployeeId().getId() + " already in storage",
                exception.getMessage());
        verify(employeeDao, times(1)).findById(employee.getEmployeeId().getId());
        verifyNoMoreInteractions(employeeDao);
    }
}