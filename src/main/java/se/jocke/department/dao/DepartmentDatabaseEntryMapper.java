package se.jocke.department.dao;

import se.jocke.department.entity.Department;
import se.jocke.department.entity.DepartmentId;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentDatabaseEntryMapper {
    public static Department map(DepartmentDatabaseEntry departmentDatabaseEntry) {
        return Department.builder()
                .departmentId(DepartmentId.builder().id(departmentDatabaseEntry.getDepartmentId()).build())
                .departmentName(departmentDatabaseEntry.getDepartmentName())
                .build();
    }
    public static DepartmentDatabaseEntry map(Department department) {
        return DepartmentDatabaseEntry.builder()
                .departmentId(department.getDepartmentId().getId())
                .departmentName(department.getDepartmentName())
                .build();
    }
    public static List<Department> map (List<DepartmentDatabaseEntry> departmentDatabaseEntries) {
        return departmentDatabaseEntries.stream().map(DepartmentDatabaseEntryMapper::map).collect(Collectors.toList());
    }
}
