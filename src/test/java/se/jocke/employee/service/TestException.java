package se.jocke.employee.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.EmployeeDao;
import se.jocke.dao.EntityNotFoundException;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class TestException {

    @Mock
    EmployeeDao employeeDao;
    @InjectMocks
    EmployeeService systemUnderTest = new EmployeeServiceImpl();

    //Sätter up en regel för vad som händer när metoden söker på ID 999 och inte hittar den,
    //Då ska den kasta en EntityNotFoundException//.
    @BeforeEach
    public void setUp(){
    when(employeeDao.findById(999)).thenThrow(EntityNotFoundException.class);
    }


    @Test   //Testar att koden skickar en EntityNotFoundException
    public void testEntityNotFoundException() {
        Assertions.assertThrows(EntityNotFoundException.class, () -> systemUnderTest.getEmployeeById(999));
    }
}
