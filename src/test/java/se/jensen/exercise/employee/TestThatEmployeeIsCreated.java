package se.jensen.exercise.employee;

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
        Integer departmentId = 2;

        Employee employee = Employee.builder()
                .employeeId(employeeId)
                .firstName(firstname)
                .lastName(lastName)
                .fullTime(fullTime)
                .salary(salary)
                .departmentId(departmentId)
                .build();

        Assert.assertEquals((Integer)5,employee.getEmployeeId());
        Assert.assertEquals(firstname,employee.getFirstName());
        Assert.assertEquals(lastName,employee.getLastName());
        Assert.assertEquals(salary,employee.getSalary());
        Assert.assertEquals(fullTime,employee.getFullTime());
    }
}
