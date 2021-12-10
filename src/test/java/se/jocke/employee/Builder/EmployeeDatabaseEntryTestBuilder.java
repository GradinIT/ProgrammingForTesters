package se.jocke.employee.Builder;

import se.jocke.employee.dao.EmployeeDatabaseEntry;

public class EmployeeDatabaseEntryTestBuilder {

        public static EmployeeDatabaseEntry build() {
            return EmployeeDatabaseEntry.builder()
                    .employeeId(EmployeeBuilderFixture.EMPLOYEE_ID.getId())
                    .firstName(EmployeeBuilderFixture.FIRSTNAME)
                    .lastName(EmployeeBuilderFixture.LASTNAME)
                    .fullTime(EmployeeBuilderFixture.FULLTIME)
                    .salary(EmployeeBuilderFixture.SALARY)
                    .departmentId(EmployeeBuilderFixture.DEPARTMENTID)
                    .build();

        }
        }