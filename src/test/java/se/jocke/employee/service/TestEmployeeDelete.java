package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.dao.mapper.EmployeePojoMapper;
import se.jocke.department.entity.Employee;
import se.jocke.department.entity.EmployeeID;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;

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
        employeedb=EmployeePojoMapper.map(employee);


    }
    @Test
    public void delete() {
        employee=systemUnderTest.removeEmployee(employee);

    }
}
