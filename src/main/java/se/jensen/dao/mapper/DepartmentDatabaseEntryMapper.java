package se.jensen.dao.mapper;

import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.entity.Department;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentDatabaseEntryMapper {
    public static Department map(DepartmentDatabaseEntry departmentDatabaseEntry) {
        return Department.builder()
                .departmentId(departmentDatabaseEntry.getDepartmentId())
                .departmentName(departmentDatabaseEntry.getDepartmentName())
                .build();
    }
    public static DepartmentDatabaseEntry map(Department department) {
        return DepartmentDatabaseEntry.builder()
                .departmentId(department.getDepartmentId())
                .departmentName(department.getDepartmentName())
                .build();
    }
    public static List<Department> map (List<DepartmentDatabaseEntry> departmentDatabaseEntries) {
        return departmentDatabaseEntries.stream().map(DepartmentDatabaseEntryMapper::map).collect(Collectors.toList());
    }
}
