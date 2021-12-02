package se.jocke.employee.test.builder;

import lombok.Builder;
import lombok.Getter;
import se.jocke.employee.unittest.entity.Employee;

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
