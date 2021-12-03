package se.jocke.department.test.builder;

import se.jocke.department.api.DepartmentModel;

public class DepartmentModelTestBuilder {
    public static DepartmentModel build() {
        return DepartmentModel.builder()
                .departmentId(DepartmentTestFixture.id)
                .departmentName(DepartmentTestFixture.name)
                .build();
    }
}
