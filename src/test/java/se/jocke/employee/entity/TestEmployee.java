package se.jocke.employee.entity;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import se.jocke.employee.builder.EmployeeTestBuilder;


public class TestEmployee {
    private static final Employee EMPLOYEE = EmployeeTestBuilder.build();

    @Test
    public void testThatNullCheckWorks(){
        Assertions.assertThrows(NullPointerException.class , () -> Employee.builder()
                .firstName(EMPLOYEE.getFirstName())
                .lastName(EMPLOYEE.getLastName())
                .fullTime(EMPLOYEE.getFullTime())
                .salary(EMPLOYEE.getSalary())
                .departmentId(EMPLOYEE.getDepartmentId())
                .build());
    }
}
