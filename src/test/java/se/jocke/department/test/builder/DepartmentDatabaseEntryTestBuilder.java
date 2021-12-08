package se.jocke.department.test.builder;

import se.jocke.department.dao.DepartmentDatabaseEntry;

import java.util.Arrays;
import java.util.List;

public class DepartmentDatabaseEntryTestBuilder {
    public static DepartmentDatabaseEntry build() {
        return DepartmentDatabaseEntry.builder()
                .departmentId(DepartmentTestFixture.id)
                .departmentName(DepartmentTestFixture.name)
                .build();
    }

    public static List<DepartmentDatabaseEntry> buildList() {
        return Arrays.asList(DepartmentDatabaseEntry.builder()
                .departmentId(DepartmentTestFixture.id)
                .departmentName(DepartmentTestFixture.name)
                .build());
    }
}
