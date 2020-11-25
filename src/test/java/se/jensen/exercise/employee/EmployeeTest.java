package se.jensen.exercise.employee;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import se.jensen.entity.Employee;
import se.jensen.exercise.EmployeeTestBuilder;
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
                .employeeId(employeeId)
                .firstName(firstname)
                .lastName(lastName)
                .fullTime(fullTime)
                .departmentId(1)
                .salary(salary)
                .build();

        Assert.assertEquals(employeeId,employee.getEmployeeId());
        Assert.assertEquals("ove",employee.getFirstName());
        Assert.assertEquals(lastName,employee.getLastName());
        Assert.assertEquals(salary,employee.getSalary());
        Assert.assertEquals(fullTime,employee.getFullTime());
    }

    @Test(expected = NullPointerException.class)
    public void testThatNullValueNotAllowedForEmployeeID() {
        Employee.builder()
                .employeeId(4)
                .firstName("")
                .lastName("")
                .salary(null)
                .fullTime(Boolean.TRUE)
                .departmentId(1)
                .build();
    }

    @Test
    public void testThatToStringReturnsProper(){
        String expectedToString = EmployeeTestBuilder.build().toString();
        Employee employee = EmployeeTestBuilder.build();
        Assert.assertEquals(expectedToString,employee.toString());
    }
}
