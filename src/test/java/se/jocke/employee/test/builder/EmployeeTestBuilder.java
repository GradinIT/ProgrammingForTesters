package se.jocke.employee.test.builder;

import se.jocke.department.entity.Department;
import se.jocke.department.entity.DepartmentId;
import se.jocke.department.test.builder.DepartmentTestFixture;
import se.jocke.employee.entity.Employee;
import se.jocke.employee.entity.EmployeeID;

public class EmployeeTestBuilder {

        public static Employee build() {
            return  Employee.builder()
                    .employeeId(EmployeeTestFixture.id)
                    .firstName(EmployeeTestFixture.firstName)
                    .lastName(EmployeeTestFixture.lastName)
                    .salary(EmployeeTestFixture.salary)
                    .fullTime(EmployeeTestFixture.fulltime)
                    .departmentId(EmployeeTestFixture.departmetId)
                    .build();
        }

}
