package se.jocke.employee.test.builder;

import se.jocke.employee.dao.EmployeeDatabaseEntry;
import se.jocke.employee.entity.Employee;

import java.math.BigDecimal;

public class EmployeeDatabaseEntryTestBuilder {
    public static EmployeeDatabaseEntry bygg() {
        return EmployeeDatabaseEntry.builder()
                .employeeId(EmployeeTestFixture.employeeID)
                .firstName(EmployeeTestFixture.firstName)
                .lastName(EmployeeTestFixture.lastName)
                .salary(new BigDecimal(EmployeeTestFixture.salary))
                .fullTime(EmployeeTestFixture.fullTime)
                .departmentId(EmployeeTestFixture.departmentID)
                .build();
    }
}
