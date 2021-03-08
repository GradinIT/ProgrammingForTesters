package se.jocke.employee.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;


public class TestCreateEmployee {

    private final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();

    @Test
    public void testCreateEmployee(){
        Employee employee = Employee
                .builder()
                    .employeeId(EMPLOYEE.getEmployeeId())
                    .firstName(EMPLOYEE.getFirstName())
                    .lastName(EMPLOYEE.getLastName())
                    .fullTime(EMPLOYEE.getFullTime())
                    .salary(EMPLOYEE.getSalary())
                    .departmentId(EMPLOYEE.getDepartmentId())
                .build();

        Assertions.assertEquals(EMPLOYEE, employee);
    }

@Test
    public void testThrowsNullPointerException() {

       Assertions.assertThrows(NullPointerException.class, () -> {
           Employee.builder().firstName(EMPLOYEE.getFirstName()).build();
       }) ;
    }

}
