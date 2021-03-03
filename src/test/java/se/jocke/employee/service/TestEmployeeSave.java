package se.jocke.employee.service;

import io.cucumber.java.an.E;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.dao.mapper.EmployeePojoMapper;
import se.jocke.department.entity.Employee;
import se.jocke.department.entity.EmployeeID;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeSave{
    @Mock
    EmployeeDao employeeDao;
    @InjectMocks
    EmployeeService systemUnderTest = new EmployeeServiceImpl();
    @Mock
    EmployeeDatabaseEntry empdb;
    @Mock
    Employee employee;


    @BeforeEach
    public void setUp() {
        empdb = EmployeeDatabaseEntry.builder()
                .employeeId(EmployeeID.builder().id(5).build().getId())
                .firstName("java")
                .lastName("javasson")
                .salary(BigDecimal.valueOf(25000.00))
                .fullTime(true)
                .departmentId(5).build();
        employee = EmployeePojoMapper.map(empdb);
        when(employeeDao.save(EmployeePojoMapper.map(employee))).thenReturn(empdb);
    }

    @Test
    public void save() {
        Employee emp = systemUnderTest.createOrUpdateEmployee(employee);
        Assertions.assertEquals("java", emp
                .getFirstName());
        Assertions.assertEquals("javasson", emp.getLastName());
    }


}
