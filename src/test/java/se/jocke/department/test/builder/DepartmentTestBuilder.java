package se.jocke.department.test.builder;

import se.jocke.department.unittest.entity.Department;

public class DepartmentTestBuilder {
    public static Department build() {
        return  Department.builder()
                .departmentId(DepartmentTestFixture.id)
                .departmentName(DepartmentTestFixture.name)
                .build();
    }
}