package se.jocke.department.unittest.test.builder;


import se.jocke.employee.dao.EmployeeDatabaseEntry;
import se.jocke.employee.entity.EmployeeTestFixture;

public class EmployeeDatabaseEntryTestBuilder {
    public static EmployeeDatabaseEntry build() {
        return EmployeeDatabaseEntry.builder()
                .employeeId(EmployeeTestFixture.id.getId())
                .firstName(EmployeeTestFixture.firstName)
                .lastName(EmployeeTestFixture.lastName)
                .salary(EmployeeTestFixture.salary)
                .departmentId(EmployeeTestFixture.departmentId)
                .fullTime(EmployeeTestFixture.fullTime)
                .build();
    }
}