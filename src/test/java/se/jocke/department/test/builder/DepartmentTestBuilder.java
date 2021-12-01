package se.jocke.department.test.builder;

import se.jocke.department.entity.Department;
import se.jocke.department.entity.DepartmentId;

public class DepartmentTestBuilder {
    public static Department build() {
        return  Department.builder()
                .departmentId(DepartmentId.builder().id(DepartmentTestFixture.id).build())
                .departmentName(DepartmentTestFixture.name)
                .build();
    }
}
