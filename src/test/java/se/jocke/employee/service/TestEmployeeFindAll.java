package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.department.entity.Employee;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class TestEmployeeFindAll {

    @Mock
    EmployeeDao employeeDao;
    @InjectMocks
    EmployeeService systemUnderTest = new EmployeeServiceImpl();

    @BeforeEach
    public void setUp(){
        when(employeeDao.findAll()).thenReturn(List.of(EmployeeDatabaseEntry.builder()
                .firstName("firstName1")
                .lastName("lastName1")
                .departmentId(1)
                .employeeId(1)
                .salary(BigDecimal.valueOf(25000.00))
                .fullTime(true).build(), EmployeeDatabaseEntry.builder()
                .firstName("firstName2")
                .lastName("lastName2")
                .departmentId(2)
                .employeeId(2)
                .salary(BigDecimal.valueOf(25000.00))
                .fullTime(true).build()));


    }


    @Test
    public void findAll(){
        List<Employee> testlist = systemUnderTest.getAllEmployees();
        Assertions.assertEquals(2,testlist.size());
    }

}
