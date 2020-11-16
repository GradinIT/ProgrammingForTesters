package se.jensen.exercise;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.entity.Employee;

import java.math.BigDecimal;

public class TestCreateEmployee {
    @Test
    public void testThatEmployeeIsCreated() {
        //TODO: create an Employee Instance
        //Assert instance is not null
        //Assert thar all fields ar equal
        Integer EMPLOYEEID = 1;
        String FIRSTNAME = "Firstname";
        String LASTNAME = "Lastname";
        Boolean FULLTIME = true; // Boolean.TRUE;
        BigDecimal SALARY = BigDecimal.valueOf(11000.25);

        Employee employee = Employee.builder()
                .setEmployeeId(EMPLOYEEID)
                .setFirstName(FIRSTNAME)
                .setLastName(LASTNAME)
                .setFullTime(FULLTIME)
                .setSalary(SALARY)
                .build();

        Assert.assertNotNull(employee); //check that employee is not null

        Assert.assertEquals(EMPLOYEEID,employee.getEmployeeId()); // check that employee have correct id
        Assert.assertEquals(FIRSTNAME,employee.getFirstName());
        Assert.assertEquals(LASTNAME,employee.getLastName());
        Assert.assertEquals(FULLTIME,employee.getFullTime());
        Assert.assertEquals(SALARY,employee.getSalary());
    }
}
