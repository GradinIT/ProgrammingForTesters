package se.jensen.exercise.employee;

import liquibase.pro.packaged.I;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import se.jensen.dao.EmployeeDao;
import se.jensen.dao.EmployeeDatabaseEntry;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TestThatEmployeeIsStoredInDatabase {

    EmployeeDao employeeDao = mock(EmployeeDao.class); // Mock the DB

    private final Integer EMPLOYEEID = 10;
    private final String FIRSTNAME = "Arne";
    private final String LASTNAME = "Andersson";
    private final Boolean FULLTIME = true;
    private final BigDecimal SALARY = BigDecimal.valueOf(25000.0);

    @Before
    public void setUpMock() {
        when(employeeDao.save(any(EmployeeDatabaseEntry.class)))
                .thenReturn(EmployeeDatabaseEntry.builder()
                        .employeeId(EMPLOYEEID)
                        .firstName(FIRSTNAME)
                        .lastName(LASTNAME)
                        .fullTime(FULLTIME)
                        .salary(SALARY)
                        .build());
    }
    @Test
    public void testIsStored() {
        EmployeeDatabaseEntry employeeDatabaseEntry = EmployeeDatabaseEntry.builder()
                .employeeId(EMPLOYEEID)
                .firstName(FIRSTNAME)
                .lastName(LASTNAME)
                .fullTime(FULLTIME)
                .salary(SALARY)
                .build();

        EmployeeDatabaseEntry employeeDatabaseEntrySaved = employeeDao.save(employeeDatabaseEntry);
        Assert.assertEquals(employeeDatabaseEntry.getEmployeeId(),employeeDatabaseEntrySaved.getEmployeeId());
    }
}
