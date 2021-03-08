package se.jocke.employee.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;

public class TestCreateEmployee {  // 2/3-21 Test past by default values. No Mocking done yet.

    private final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();

    @Test
    public void testCreateEmployee() {  // Test passed from start.
        Employee employee = Employee.builder()
                .departmentId(EMPLOYEE.getDepartmentId())
                .employeeId(EMPLOYEE.getEmployeeId())
                .firstName(EMPLOYEE.getFirstName())
                .lastName(EMPLOYEE.getLastName())
                .salary(EMPLOYEE.getSalary())
                .fullTime(EMPLOYEE.getFullTime())
                .departmentId(EMPLOYEE.getDepartmentId())
                .build();
        Assertions.assertEquals(EMPLOYEE, employee);
        Assertions.assertEquals(EMPLOYEE.getEmployeeId(), employee.getEmployeeId());
        Assertions.assertEquals(EMPLOYEE.getFirstName(), employee.getFirstName());
        Assertions.assertEquals(EMPLOYEE.getLastName(), employee.getLastName());
        Assertions.assertEquals(EMPLOYEE.getSalary(), employee.getSalary());
        Assertions.assertEquals(EMPLOYEE.getFullTime(), employee.getFullTime());
        Assertions.assertEquals(EMPLOYEE.getDepartmentId(), employee.getDepartmentId());
    }

    @Test // Original test, keep this one!
    public void testCreateEmployeeThrowsException() { // Test passed from start.
        Assertions.assertThrows(NullPointerException.class, () ->{
            Employee.builder().firstName(EMPLOYEE.getFirstName()).build();
        });
    }

   /* @Test // Ciccis own test
    public void makeTheTestFailTestCreateEmployeeThrowsException() { // Giving all arguments to the builder, thus making the asssertThrow test fail.
        Assertions.assertThrows(NullPointerException.class, () ->{ // result :"Expected java.lang.NullPointerException to be thrown, but nothing was thrown."
            Employee.builder().departmentId(EMPLOYEE.getDepartmentId())
                    .employeeId(EMPLOYEE.getEmployeeId())
                    .firstName(EMPLOYEE.getFirstName())
                    .lastName(EMPLOYEE.getLastName())
                    .salary(EMPLOYEE.getSalary())
                    .fullTime(EMPLOYEE.getFullTime())
                    .departmentId(EMPLOYEE.getDepartmentId())
                    .build();
        });
    }*/

}
