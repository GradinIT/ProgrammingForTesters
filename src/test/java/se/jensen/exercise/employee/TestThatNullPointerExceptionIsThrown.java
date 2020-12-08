package se.jensen.exercise.employee;

import org.junit.Test;
import se.jensen.entity.Employee;
import se.jensen.entity.EmployeeID;

import java.math.BigDecimal;

public class TestThatNullPointerExceptionIsThrown {
    @Test(expected = NullPointerException.class)
    public void testSalaryNull() {
        Employee.builder()
                .employeeId(EmployeeID.builder().id(4).build())
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
                .employeeId(EmployeeID.builder().id(4).build())
                .firstName(null)
                .lastName("")
                .salary(BigDecimal.valueOf(10))
                .fullTime(Boolean.TRUE)
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void testLastNameNUll() {
        Employee.builder()
                .employeeId(EmployeeID.builder().id(4).build())
                .firstName("")
                .lastName(null)
                .salary(BigDecimal.valueOf(10))
                .fullTime(Boolean.TRUE)
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void testFullTimeNull() {
        Employee.builder()
                .employeeId(EmployeeID.builder().id(4).build())
                .firstName("")
                .lastName("")
                .salary(BigDecimal.valueOf(10))
                .fullTime(null)
                .build();
    }
}
