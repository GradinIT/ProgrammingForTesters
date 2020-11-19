package se.jensen.exercise.employee;

import org.junit.Test;
import se.jensen.entity.Employee;

import java.math.BigDecimal;

public class TestThatNullPointerExceptionIsThrown {
    @Test(expected = NullPointerException.class)
    public void testSalaryNull() {
        Employee.builder()
                .employeeId(4)
                .firstName("")
                .lastName("")
                .salary(null)
                .fullTime(Boolean.TRUE)
                .build();
    }
    @Test(expected = NullPointerException.class)
    public void testIdNull() {
        Employee.builder()
                .employeeId(null)
                .firstName("")
                .lastName("")
                .salary(BigDecimal.valueOf(10))
                .fullTime(Boolean.TRUE)
                .build();
    }
    @Test(expected = NullPointerException.class)
    public void testFirstNameNull() {
        Employee.builder()
                .employeeId(4)
                .firstName(null)
                .lastName("")
                .salary(BigDecimal.valueOf(10))
                .fullTime(Boolean.TRUE)
                .build();
    }
    @Test(expected = NullPointerException.class)
    public void testLastNameNUll() {
        Employee.builder()
                .employeeId(4)
                .firstName("")
                .lastName(null)
                .salary(BigDecimal.valueOf(10))
                .fullTime(Boolean.TRUE)
                .build();
    }@Test(expected = NullPointerException.class)
    public void testFullTimeNull() {
        Employee.builder()
                .employeeId(4)
                .firstName("")
                .lastName("")
                .salary(BigDecimal.valueOf(10))
                .fullTime(null)
                .build();
    }
}
