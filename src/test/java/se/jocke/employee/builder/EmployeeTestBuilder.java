package se.jocke.employee.builder;

import se.jocke.department.entity.Employee;
import se.jocke.department.entity.EmployeeID;

import java.math.BigDecimal;

//få fram instanser
public class EmployeeTestBuilder {
    private static Employee.EmployeeBuilder builder = Employee.builder();

    public static Employee.EmployeeBuilder builder() {
        return builder
                .employeeId(EmployeeID.builder().id(1).build())
                .departmentId(2)
                .firstName("TestKalle")
                .lastName("TestPersson")
                .fullTime(true)
                .salary(BigDecimal.valueOf(26000.00));
    }
}
