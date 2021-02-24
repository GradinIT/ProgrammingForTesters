package se.jocke.employee.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;

public class TestCreateEmployee {

    Employee EMPLOYEE = EmployeeTestBuilder.builder().build();

    @Test
    public void TestCreateEmployee() {
        Employee emp = Employee.builder()
                .employeeId(EMPLOYEE.getEmployeeId())
                .firstName(EMPLOYEE.getFirstName())
                .lastName(EMPLOYEE.getLastName())
                .salary(EMPLOYEE.getSalary())
                .fullTime(EMPLOYEE.getFullTime())
                .departmentId(EMPLOYEE.getDepartmentId())
                .build();

        Assertions.assertEquals(EMPLOYEE.getEmployeeId().getId(),emp.getEmployeeId().getId());
        Assertions.assertEquals(EMPLOYEE.getFirstName(),emp.getFirstName());
        Assertions.assertEquals(EMPLOYEE.getLastName(),emp.getLastName());
        Assertions.assertEquals(EMPLOYEE.getSalary(),emp.getSalary());
        Assertions.assertEquals(EMPLOYEE.getFullTime(),emp.getFullTime());
        Assertions.assertEquals(EMPLOYEE.getDepartmentId(),emp.getDepartmentId());
    }
}
