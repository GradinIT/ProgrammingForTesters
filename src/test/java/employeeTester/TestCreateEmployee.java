package employeeTester;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.employee.builder.EmployeeTestBuilder;
import se.jocke.department.entity.Employee;


public class TestCreateEmployee {

    private final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();

    @Test
    public void testCreateEmployee() {
        Employee employee = EmployeeTestBuilder.builder()
                .employeeId(EMPLOYEE.getEmployeeId())
                .firstName(EMPLOYEE.getFirstName())
                .lastName(EMPLOYEE.getLastName())
                .fullTime(EMPLOYEE.getFullTime())
                .salary(EMPLOYEE.getSalary())
                .departmentId(EMPLOYEE.getDepartmentId())
                .build();

        Assertions.assertEquals(EMPLOYEE, employee);
        Assertions.assertEquals(EMPLOYEE.getEmployeeId(), employee.getEmployeeId());
        Assertions.assertEquals(EMPLOYEE.getFirstName(), employee.getFirstName());
        Assertions.assertEquals(EMPLOYEE.getLastName(), employee.getLastName());
        Assertions.assertEquals(EMPLOYEE.getFullTime(), employee.getFullTime());
        Assertions.assertEquals(EMPLOYEE.getSalary(), employee.getSalary());
        Assertions.assertEquals(EMPLOYEE.getDepartmentId(), employee.getDepartmentId());
    }

    @Test
    public void testCreateDepartmentThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            Employee.builder().firstName(EMPLOYEE.getFirstName()).build();
        });
    }
}







