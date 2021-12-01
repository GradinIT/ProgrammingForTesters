package se.jocke.department.test.builder;

import se.jocke.department.dao.DepartmentDatabaseEntry;

public class DepartmentDatabaseEntryTestBuilder {
    public static DepartmentDatabaseEntry build() {
        return DepartmentDatabaseEntry.builder()
                .departmentId(DepartmentTestFixture.id)
                .departmentName(DepartmentTestFixture.name)
                .build();
    }
}
