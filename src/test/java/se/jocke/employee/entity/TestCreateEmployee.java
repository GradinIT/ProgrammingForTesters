package se.jocke.employee.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.department.entity.Department;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;

public class TestCreateEmployee {
    private final Employee EMPLOYEE = EmployeeTestBuilder.builderMethod().build(); // DESSA TVÅ

    @Test
    public void testCreateEmployee() {
        Employee employee = EmployeeTestBuilder.builderMethod() // ÄR SAMMA SAK
                .employeeId(EMPLOYEE.getEmployeeId())
                .firstName(EMPLOYEE.getFirstName())
                .lastName(EMPLOYEE.getLastName())
                .salary(EMPLOYEE.getSalary())
                .fullTime(EMPLOYEE.getFullTime())
                .departmentId(EMPLOYEE.getDepartmentId())
                .build(); // ^ om jag suddar ut attributen blir objekten inte längre equal, men likadana

        Assertions.assertEquals(EMPLOYEE, employee);
        //Assertions.assertNotEquals();
//        Assertions.assertEquals(EMPLOYEE, employee);
//        Assertions.assertEquals(EMPLOYEE.getEmployeeId(), employee.getEmployeeId());
//        Assertions.assertEquals(EMPLOYEE.getFirstName(), employee.getFirstName());
//        Assertions.assertEquals(EMPLOYEE.getLastName(), employee.getLastName());
//        Assertions.assertEquals(EMPLOYEE.getSalary(), employee.getSalary());
//        Assertions.assertEquals(EMPLOYEE.getFullTime(), employee.getFullTime());
//        Assertions.assertEquals(EMPLOYEE.getDepartmentId(), employee.getDepartmentId());
//        Assertions.assertEquals(EMPLOYEE, employee);
    }

    @Test
    public void testCreateDepartmentThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            Employee.builder().firstName(EMPLOYEE.getFirstName()).build();
        });
    }
}
