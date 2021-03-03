package se.jocke.employee.service;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jocke.dao.EmployeeDao;
import se.jocke.service.EmployeeService;
import se.jocke.service.EmployeeServiceImpl;
@ExtendWith(MockitoExtension.class)
public class TestEmployeeUpdate {
    @Mock
    EmployeeDao employeeDao;
    @InjectMocks
    EmployeeService systemUnderTest = new EmployeeServiceImpl();

@BeforeEach
public void setUp(){

}
@Test
public void update(){

}

}
