package se.jocke.employee.Builder;

import se.jocke.department.entity.Department;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.entity.EmployeeID;

public class EmployeeTestBuilder {
    public static Employee.EmployeeBuilder builder() {
        return Employee.builder().employeeId(EmployeeID.builder().id(2).build())
                .firstName("Joakim")
                .lastName("Gradin")
                .salary()
                .fullTime()
                .departmentId();

    }
}
