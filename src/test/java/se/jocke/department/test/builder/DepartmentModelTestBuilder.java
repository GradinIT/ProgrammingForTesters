package se.jocke.department.test.builder;

import se.jocke.department.unittest.api.EmployeeModel;

public class DepartmentModelTestBuilder {
    public static EmployeeModel build() {
        return EmployeeModel.builder()
                .departmentId(DepartmentTestFixture.id)
                .departmentName(DepartmentTestFixture.name)
                .build();
    }
}
