package se.jocke.employee.entity;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TestEmployee {
    private static final Employee EMPLOYEE = EmployeeTestBuilder.build();

    @Test
    public void testThatNullCheckWorks(){
        Assertions.assertThrows(NullPointerException.class , () -> Employee.builder()
                .employeeName(EMPLOYEE.getEmployeeName()).build());
    }
}
