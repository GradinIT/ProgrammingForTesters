package se.jocke.employee.service;

import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.And;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.dao.EntityNotFoundException;
import se.jocke.dao.mapper.EmployeePojoMapper;
import se.jocke.department.entity.Employee;
import se.jocke.department.entity.EmployeeID;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class TestEmployeeDelete {

    @Mock
    EmployeeDao employeeDao;
    @InjectMocks
    EmployeeService systemUnderTest = new EmployeeServiceImpl();
    @Mock
    Employee employee;
    @Mock
    EmployeeDatabaseEntry employeedb;


    @BeforeEach
    public void setUp() {
        employee = Employee.builder()
                .employeeId(EmployeeID.builder().id(20).build())
                .firstName("java")
                .lastName("javaman")
                .salary(BigDecimal.valueOf(25000.00))
                .fullTime(true)
                .departmentId(5).build();
        employeedb = EmployeePojoMapper.map(employee);  //Gör om employee till ett db objekt.
        when(employeeDao.findById(employee.getEmployeeId().getId())).thenReturn(Optional.of(employeedb));
    }

    @Test
    public void findById() {
        employee = systemUnderTest.removeEmployee(employee); // Ta bort employee med nummer 20
        verify(employeeDao, times(1)).delete(employeedb);

        verify(employeeDao, times(2)).findById(employee.getEmployeeId().getId());

    }


}
