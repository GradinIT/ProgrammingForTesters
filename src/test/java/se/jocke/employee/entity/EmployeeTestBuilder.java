package se.jocke.employee.entity;

public class EmployeeTestBuilder {
    public static Employee build(){
        return Employee.builder()
                .employeeId(EmployeeTestFixture.id)
                .employeeName(EmployeeTestFixture.name)
                .build();
    }
}
