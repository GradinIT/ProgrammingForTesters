package se.jocke.employee.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;

public class TestCreateEmployee {
    /* MALL
    private final Department DEPARTMENT = DepartmentTestBuilder.builder().build(); */

    private final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();

    /*MALL
    *  @Test
    public void testCreateDepartment() {
        Department department = DepartmentTestBuilder.builder()
                .departmentId(DEPARTMENT.getDepartmentId())
                .departmentName(DEPARTMENT.getDepartmentName())
                .build();
        Assertions.assertEquals(DEPARTMENT, department);
        Assertions.assertEquals(DEPARTMENT.getDepartmentName(), department.getDepartmentName());
        Assertions.assertEquals(DEPARTMENT.getDepartmentId(), department.getDepartmentId());
    } */

    @Test
    public void testCreateEmployee() {  // Test passed from start.
        Employee employee = EmployeeTestBuilder.builder()
                .departmentId(EMPLOYEE.getDepartmentId())
                .employeeId(EMPLOYEE.getEmployeeId())
                .firstName(EMPLOYEE.getFirstName())
                .fullTime(EMPLOYEE.getFullTime())
                .lastName(EMPLOYEE.getLastName())
                .salary(EMPLOYEE.getSalary())
                .build();
        Assertions.assertEquals(EMPLOYEE, employee);
        Assertions.assertEquals(EMPLOYEE.getDepartmentId(), employee.getDepartmentId());
        Assertions.assertEquals(EMPLOYEE.getEmployeeId(), employee.getEmployeeId());
        Assertions.assertEquals(EMPLOYEE.getFirstName(), employee.getFirstName());
        Assertions.assertEquals(EMPLOYEE.getFullTime(), employee.getFullTime());
        Assertions.assertEquals(EMPLOYEE.getLastName(), employee.getLastName());
        Assertions.assertEquals(EMPLOYEE.getSalary(), employee.getSalary());
    }
        /* MALL
        * @Test
    public void testCreateDepartmentThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            Department.builder().departmentName(DEPARTMENT.getDepartmentName()).build();
        });
    }*/
        @Test
        public void testCreateEmployeeThrowsException() { // Test passed from start.
            Assertions.assertThrows(NullPointerException.class, () ->{
                Employee.builder().firstName(EMPLOYEE.getFirstName()).build();
            });
        }

}
