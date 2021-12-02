package se.jocke.employee.test.builder;

import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.dao.EmployeeDatabaseEntry;

public class EmployeeDatabaseEntryTestBuilder {

        public static EmployeeDatabaseEntry build() {
            return EmployeeDatabaseEntry.builder()
                    .employeeId(EmployeeTestFixture.id.getId())
                    .firstName(EmployeeTestFixture.firstName)
                    .lastName(EmployeeTestFixture.lastName)
                    .salary(EmployeeTestFixture.salary)
                    .fullTime(EmployeeTestFixture.fulltime)
                    .departmentId(EmployeeTestFixture.departmentId)
                    .build();

        }
    }