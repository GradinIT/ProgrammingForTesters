package se.jocke.employee.service;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.dao.EntityAlreadyInStorageException;
import se.jocke.dao.EntityNotFoundException;
import se.jocke.dao.mapper.EmployeePojoMapper;
import se.jocke.department.entity.Employee;
import se.jocke.department.entity.EmployeeID;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;
import se.jocke.util.ObjectUtility;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TestEmployeeUpdate {
    @Mock
    EmployeeDao employeeDao;
    @InjectMocks
    EmployeeService systemUnderTest = new EmployeeServiceImpl();
    @Mock
    Employee employee;


@BeforeEach
public void setUp(){
    employee = Employee.builder()
            .employeeId(EmployeeID.builder().id(5).build())
            .firstName("avaj")
            .lastName("nossavaj")
            .salary(BigDecimal.valueOf(25000.00))
            .fullTime(true)
            .departmentId(5).build();
    when(employeeDao.findById(employee.getEmployeeId().getId()))
            .thenReturn(Optional.of(EmployeePojoMapper.map(employee)));
    when(employeeDao.save(EmployeePojoMapper.map(employee))).thenReturn(EmployeePojoMapper.map(employee));

}
@Test
public void update(){
employee=systemUnderTest.updateEmployee(employee);
List<Object> EmpAttrList = new ObjectUtility().ObjectUtility(employee);
    boolean result = EmpAttrList.stream().anyMatch(e->e.equals(null));
Assertions.assertFalse(result);
verify(employeeDao,times(1)).save(EmployeePojoMapper.map(employee));
verify(employeeDao,times(1)).findById(employee.getEmployeeId().getId());

}

}
