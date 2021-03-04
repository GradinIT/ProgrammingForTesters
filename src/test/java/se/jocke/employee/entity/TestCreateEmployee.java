package se.jocke.employee.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.department.builder.DepartmentTestBuilder;
import se.jocke.department.entity.Department;
import se.jocke.department.entity.Employee;
import se.jocke.employee.builder.EmployeeTestBuilder;

public class TestCreateEmployee {


        // skapar ett employee objekt utav våran EmployeeTestBuilder
        private final Employee EMPLOYEE = EmployeeTestBuilder.builder().build();

        @Test
        public void testCreateEmployee() {
            //skapar ett nytt objekt "employee" med Testbuildern och lägger in värderna från "EMPLOYEE"
            // sen ser vi om värderna matchar
            Employee employee = EmployeeTestBuilder.builder()
                    .employeeId(EMPLOYEE.getEmployeeId())
                    .firstName(EMPLOYEE.getFirstName())
                    .lastName(EMPLOYEE.getLastName())
                    .build();
            Assertions.assertEquals(EMPLOYEE, employee);
            Assertions.assertEquals(EMPLOYEE.getFirstName(), employee.getFirstName());
            Assertions.assertEquals(EMPLOYEE.getLastName(), employee.getLastName());
            Assertions.assertEquals(EMPLOYEE.getEmployeeId(), employee.getEmployeeId());
        }

        @Test
        public void testCreateEmployeeThrowsException() {
            // Letar efter firstname variabeln och kastar ett exeption om den är null
            Assertions.assertThrows(NullPointerException.class, () -> {
                Employee.builder().firstName(EMPLOYEE.getFirstName()).build();
            });
        }
    }

