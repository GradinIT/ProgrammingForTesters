package se.jocke.employee.builder;

import se.jocke.employee.builder.EmployeeTestFixture;
import se.jocke.employee.dao.EmployeeDatabaseEntry;

public class EmployeeDatabaseEntryTestBuilder {
    public static EmployeeDatabaseEntry build() {
        return EmployeeDatabaseEntry.builder()
                .employeeId(EmployeeTestFixture.employeeId.getId())
                .firstName(EmployeeTestFixture.firstName)
                .lastName(EmployeeTestFixture.lastName)
                .fullTime(EmployeeTestFixture.fullTime)
                .salary(EmployeeTestFixture.salary)
                .departmentId(EmployeeTestFixture.departmentId)
                .build();
    }
}