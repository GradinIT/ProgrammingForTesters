package se.jocke.employee.builder;


import se.jocke.department.unittest.api.DepartmentModel;
import se.jocke.employee.api.EmployeeModel;
import se.jocke.employee.entity.EmployeeID;
import se.jocke.employee.entity.EmployeeTestFixture;

import javax.annotation.Nonnull;
import java.math.BigDecimal;

public class EmployeeModelTestBuilder {

    public static EmployeeModel build() {
        return EmployeeModel.builder()
              // .employeeId(EmployeeTestFixture.id)
                .employeeId(EmployeeTestFixture.id)
                .departmentId(EmployeeTestFixture.departmentId)
                .firstName(EmployeeTestFixture.firstName)
                .lastName(EmployeeTestFixture.lastName)
                .salary(EmployeeTestFixture.salary)
                .fullTime(EmployeeTestFixture.fullTime)
                .build();
    }
     /*   @Nonnull
        private Integer employeeId;
        @Nonnull
        private String firstName;
        @Nonnull
        private String lastName;
        @Nonnull
        private BigDecimal salary;
        @Nonnull
        private Boolean fullTime;
        @Nonnull
        private Integer departmentId; */


    }


