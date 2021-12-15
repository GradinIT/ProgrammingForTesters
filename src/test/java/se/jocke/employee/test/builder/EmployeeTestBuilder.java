package se.jocke.employee.test.builder;

import se.jocke.employee.entity.Employee;

public class EmployeeTestBuilder {

        public static Employee build() {
            return  Employee.builder()
                    .employeeId(EmployeeTestFixture.id)
                    .firstName(EmployeeTestFixture.firstName)
                    .lastName(EmployeeTestFixture.lastName)
                    .salary(EmployeeTestFixture.salary)
                    .fullTime(EmployeeTestFixture.fullTime)
                    .departmentId(EmployeeTestFixture.departmentId)
                    .build();
        }

}
