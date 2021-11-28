package se.jocke.employee.entity;

import org.junit.Test;
import org.junit.jupiter.api.*;


import static org.assertj.core.api.Assumptions.assumeThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;


public class TestEmployee {
    private static final Employee EMPLOYEE = EmployeeTestBuilder.build();


    @Test
    public void testThatEmployeeIsCreated (){
        Employee employee = Employee.builder()
                .employeeId(EMPLOYEE.getEmployeeId())
                .firstName(EMPLOYEE.getFirstName())
                .departmentId(EMPLOYEE.getDepartmentId())
                .lastName(EMPLOYEE.getLastName())
                .salary(EMPLOYEE.getSalary())
                .fullTime(EMPLOYEE.getFullTime()) // alla .deklartioner måste vara med annars är dom null
                .build();  // kom ihåg att skriva .build() när man skapar en builder

        String EMPLOYEE_SRING_VALUE = EMPLOYEE.toString();
        String emplyee_string_value = employee.toString();
        Assertions.assertEquals(EMPLOYEE.toString(), employee.toString());

        Assertions.assertEquals(EMPLOYEE, employee); //jämför hårdkodade EMPLOYEE med emplyeen skapad med builder ovan
        Assertions.assertEquals(EMPLOYEE.getEmployeeId(), employee.getEmployeeId());
        Assertions.assertEquals(EMPLOYEE.getFirstName(), employee.getFirstName());
        Assertions.assertEquals(EMPLOYEE.getDepartmentId(), employee.getDepartmentId());
        Assertions.assertEquals(EMPLOYEE.getLastName(), employee.getLastName());
        Assertions.assertEquals(EMPLOYEE.getSalary(),employee.getSalary());
    }

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


    @Test
    public void testThatNullpointerisRaisedWhenOnlyProvidingSalary() {
        Assertions.assertThrows(NullPointerException.class, //kollar att vi får ett nullpointexception
                () -> Employee.builder().salary(EMPLOYEE.getSalary()).build()); //När vi kör .builder på EMPLOYEE med bara salary

    }
}
