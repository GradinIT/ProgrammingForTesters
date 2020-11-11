package se.jensen.exercise;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import se.jensen.entity.Employee;
import se.jensen.test.category.UnitTest;

import java.math.BigDecimal;
@Category(UnitTest.class)
public class EmployeeTest {
    @Test
    public void testThatEmployeeIsCreatedCorrectly() {
        Integer employeeId = 1;
        String firstname = "firstName";
        String lastName ="lastName";
        BigDecimal salary = BigDecimal.valueOf(10000.0);
        Boolean fullTime = Boolean.FALSE;

        Employee employee = Employee.builder()
                .setEmployeeId(employeeId)
                .setFirstName(firstname)
                .setLastName(lastName)
                .setFullTime(fullTime)
                .setSalary(salary)
                .build();

        Assert.assertEquals(employeeId,employee.getEmployeeId());
        Assert.assertEquals(firstname,employee.getFirstName());
        Assert.assertEquals(lastName,employee.getLastName());
        Assert.assertEquals(salary,employee.getSalary());
        Assert.assertEquals(fullTime,employee.getFullTime());
    }

    @Test(expected = NullPointerException.class)
    public void testThatNullValueNotAllowedForEmployeeID() {
        Employee.builder()
                .setEmployeeId(4)
                .setFirstName("")
                .setLastName("")
                .setSalary(null)
                .setFullTime(Boolean.TRUE)
                .build();
    }
}
