package se.jocke.employee.builder;

import se.jocke.department.entity.Employee;
import se.jocke.department.entity.EmployeeID;

import java.math.BigDecimal;


public class EmployeeTestBuilder {


    public static Employee.EmployeeBuilder builder() {
            return Employee.builder()
                    .employeeId(EmployeeID.builder().id(100).build())
                    .firstName("Rikard")
                    .lastName("Hedlund")
                    .salary(BigDecimal.valueOf(25000.0))
                    .fullTime(true)
                    .departmentId(1);
        }

}
