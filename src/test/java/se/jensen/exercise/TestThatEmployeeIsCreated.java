package se.jensen.exercise;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.entity.Employee;

import java.math.BigDecimal;

public class TestThatEmployeeIsCreated {
    @Test
    public void test() {
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
                .salary(salary)
                .build();

        Assert.assertEquals(employeeId,employee.getEmployeeId());
        Assert.assertEquals(firstname,employee.getFirstName());
        Assert.assertEquals(lastName,employee.getLastName());
        Assert.assertEquals(salary,employee.getSalary());
        Assert.assertEquals(fullTime,employee.getFullTime());
    }
}
