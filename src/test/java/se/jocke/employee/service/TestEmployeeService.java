package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EmployeeDatabaseEntry;
import se.jocke.department.entity.Employee;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

//Enhetstest - testar inte hela flödet
//Sätter upp miljön för mockning, så vi köra testet
@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TestEmployeeService {
    //De som vi mockar
    @Mock
    private EmployeeDao employeeDao;

    //Det som ska testas
    @InjectMocks
    private final EmployeeService systemUndertest = new EmployeeServiceImpl();

    //Används för att hålla isär testet och miljöuppsättningen.
    //Körs före varje annoterad metod @test.
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(employeeDao.findById(any(Integer.class))).thenReturn(Optional.of(EmployeeDatabaseEntry.builder()
                .employeeId(1)
                .departmentId(2)
                .firstName("TestKalle")
                .lastName("TestPersson")
                .fullTime(true)
                .salary(BigDecimal.valueOf(25000.00))
                .build()));
    }

    //Junit test
    @Test
    public void findByEmployeeId() {
        Employee employee = systemUndertest.getEmployeeById(1);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, employee.getEmployeeId().getId()),
                () -> Assertions.assertEquals("TestKalle", employee.getFirstName()),
                () -> Assertions.assertEquals("TestPersson", employee.getLastName()),
                () -> Assertions.assertEquals(2, employee.getDepartmentId()),
                () -> Assertions.assertEquals(true, employee.getFullTime()),
                () -> Assertions.assertEquals(BigDecimal.valueOf(25000.00), employee.getSalary()));
        //Kollar så att anropet har kommit till den metoden,
        //speciellt eftersom vi har injectad
        verify(employeeDao, times(1)).findById(1);
    }


}
