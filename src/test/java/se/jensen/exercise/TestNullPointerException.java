package se.jensen.exercise;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.entity.Employee;

import java.math.BigDecimal;

import static org.junit.Assert.fail;

public class TestNullPointerException {
    @Test(expected = NullPointerException.class)
    public void testThatNullPointerExceptionIsThrownWhenEmployeeIdIsNull() {
        Integer EMPLOYEEID = null;
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
    }
    @Test(expected = NullPointerException.class)
    public void testThatNullPointerExceptionIsThrownWhenFirstNameIsNull() {
        Integer EMPLOYEEID = 1;
        String FIRSTNAME = null;
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
    }
    @Test(expected = NullPointerException.class)
    public void testThatNullPointerExceptionIsThrownWhenLastNameIsNull() {
        Integer EMPLOYEEID = 1;
        String FIRSTNAME = "";
        String LASTNAME = null;
        Boolean FULLTIME = true; // Boolean.TRUE;
        BigDecimal SALARY = BigDecimal.valueOf(11000.25);

        Employee employee = Employee.builder()
                .setEmployeeId(EMPLOYEEID)
                .setFirstName(FIRSTNAME)
                .setLastName(LASTNAME)
                .setFullTime(FULLTIME)
                .setSalary(SALARY)
                .build();
    }
    @Test(expected = NullPointerException.class)
    public void testThatNullPointerExceptionIsThrownWhenSalaryIsNull() {
        Integer EMPLOYEEID = 1;
        String FIRSTNAME = "";
        String LASTNAME = "Lastname";
        Boolean FULLTIME = true; // Boolean.TRUE;
        BigDecimal SALARY = null;

        Employee employee = Employee.builder()
                .setEmployeeId(EMPLOYEEID)
                .setFirstName(FIRSTNAME)
                .setLastName(LASTNAME)
                .setFullTime(FULLTIME)
                .setSalary(SALARY)
                .build();
    }
    @Test(expected = NullPointerException.class)
    public void testThatNullPointerExceptionIsThrownWhenFullTimeIsNull() {
        Integer EMPLOYEEID = 1;
        String FIRSTNAME = "";
        String LASTNAME = "Lastname";
        Boolean FULLTIME = null; // Boolean.TRUE;
        BigDecimal SALARY = BigDecimal.valueOf(11000.25);

        Employee employee = Employee.builder()
                .setEmployeeId(EMPLOYEEID)
                .setFirstName(FIRSTNAME)
                .setLastName(LASTNAME)
                .setFullTime(FULLTIME)
                .setSalary(SALARY)
                .build();
    }

    @Test
    public void testThatMessageInExceptionIsCorrect() {
        Integer EMPLOYEEID = 1;
        String FIRSTNAME = "";
        String LASTNAME = "Lastname";
        Boolean FULLTIME = null; // Boolean.TRUE;
        BigDecimal SALARY = BigDecimal.valueOf(11000.25);
        try {
            Employee employee = Employee.builder()
                    .setEmployeeId(EMPLOYEEID)
                    .setFirstName(FIRSTNAME)
                    .setLastName(LASTNAME)
                    .setFullTime(FULLTIME)
                    .setSalary(SALARY)
                    .build();

            fail("Exected Exception not thrown");
        }
        catch (NullPointerException nullPointerException) {
            Assert.assertEquals("fullTime can't be null" ,nullPointerException.getMessage());
        }
        catch (Exception exception) {
            fail("Exected Exception not thrown");
        }
    }
}
