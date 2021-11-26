package se.jocke.employee.entity;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class EmployeeTestBuilder {
    public static Employee build(){
        return Employee.builder()
                .employeeId(EmployeeTestFixture.id)
                .firstName(EmployeeTestFixture.firstName)
                .lastName(EmployeeTestFixture.lastName)
                .salary(EmployeeTestFixture.salary)
                .fullTime(EmployeeTestFixture.fullTime)
                .departmentId(EmployeeTestFixture.departmentId)
                .build();
    }
}
