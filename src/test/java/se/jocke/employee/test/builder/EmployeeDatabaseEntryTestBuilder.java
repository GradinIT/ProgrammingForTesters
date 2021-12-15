package se.jocke.employee.test.builder;
import se.jocke.employee.dao.EmployeeDatabaseEntry;

import java.util.Arrays;
import java.util.List;

public class EmployeeDatabaseEntryTestBuilder {

        public static EmployeeDatabaseEntry build() {
            return EmployeeDatabaseEntry.builder()
                    .employeeId(EmployeeTestFixture.id.getId())
                    .firstName(EmployeeTestFixture.firstName)
                    .lastName(EmployeeTestFixture.lastName)
                    .salary(EmployeeTestFixture.salary)
                    .fullTime(EmployeeTestFixture.fullTime)
                    .departmentId(EmployeeTestFixture.departmentId)
                    .build();

        }
    public static List<EmployeeDatabaseEntry> buildList() {
        return Arrays.asList(EmployeeDatabaseEntry.builder()
                .employeeId(EmployeeTestFixture.id.getId())
                .firstName(EmployeeTestFixture.firstName)
                .lastName(EmployeeTestFixture.lastName)
                .salary(EmployeeTestFixture.salary)
                .fullTime(EmployeeTestFixture.fullTime)
                .departmentId(EmployeeTestFixture.departmentId)
                .build());
    }
    }