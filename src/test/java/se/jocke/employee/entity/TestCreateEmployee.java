package se.jocke.employee.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;


public class TestCreateEmployee {
        private final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();

        @Test
        public void testCreateEmployee() {
            Employee employee = EmployeeTestBuilder.builder()
                    .employeeId(EMPLOYEE.getEmployeeId())
                    .firstName(EMPLOYEE.getFirstName())
                    .lastName(EMPLOYEE.getLastName())
                    .salary(EMPLOYEE.getSalary())
                    .fullTime(EMPLOYEE.getFullTime())
                    .departmentId(EMPLOYEE.getDepartmentId())
                    .build();

            Assertions.assertEquals(EMPLOYEE, employee);
            Assertions.assertEquals(employee.getEmployeeId(), employee.getEmployeeId());
            Assertions.assertEquals(employee.getFirstName(), employee.getFirstName());
            Assertions.assertEquals(employee.getLastName(), employee.getLastName());
            Assertions.assertEquals(employee.getSalary(), employee.getSalary());
            Assertions.assertEquals(employee.getFullTime(), employee.getFullTime());
            Assertions.assertEquals(employee.getDepartmentId(), employee.getDepartmentId());
        }

        @Test
        public void testCreateEmployeeThrowsException() {
            Assertions.assertThrows(NullPointerException.class, () -> {
                Employee.builder().employeeId(EMPLOYEE.getEmployeeId()).build();
                Employee.builder().departmentId(EMPLOYEE.getDepartmentId()).build();
            });
        }
    }

