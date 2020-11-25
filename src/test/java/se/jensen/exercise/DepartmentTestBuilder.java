package se.jensen.exercise;

import se.jensen.entity.Department;

public class DepartmentTestBuilder {
    public static Department build() {
        return Department.builder()
                .departmentId(1)
                .departmentName("Development")
                .build();
    }
}
