package se.jocke.department.entity;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import se.jocke.employee.builder.EmployeeTestBuilder;


//Här har jag testat att det går att skapa ett employee pojo.
//Den använder sig av en testbuilder som ska innehålla samma fältvariabler som employee och jömför dem sedan.

public class TestCreateEmployee {
    @Spy
    private final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();


    @Test
    public void testCreateEmployee() {
        Employee employee = Employee.builder()
                .employeeId(EMPLOYEE.getEmployeeId())
                .departmentId(EMPLOYEE.getDepartmentId())
                .firstName(EMPLOYEE.getFirstName())
                .lastName(EMPLOYEE.getLastName())
                .salary(EMPLOYEE.getSalary())
                .fullTime(EMPLOYEE.getFullTime()).build();
        Assertions.assertAll(
                () -> Assertions.assertEquals(EMPLOYEE, employee),
                () -> Assertions.assertEquals(EMPLOYEE.getDepartmentId(), employee.getDepartmentId()),
                () -> Assertions.assertEquals(EMPLOYEE.getFirstName(), employee.getFirstName()),
                () -> Assertions.assertEquals(EMPLOYEE.getLastName(), employee.getLastName()),
                () -> Assertions.assertEquals(EMPLOYEE.getSalary(), employee.getSalary()),
                () -> Assertions.assertEquals(EMPLOYEE.getFullTime(), employee.getFullTime())

        );
    }
    @Test
    public  void testCreateEmployeeNullException(){
        Assertions.assertThrows(NullPointerException.class, ()->Employee.builder()
                .employeeId(EMPLOYEE.getEmployeeId()).build());
    }


}
