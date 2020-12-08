package se.jensen.exercise.test.builder;

import se.jensen.entity.Department;

public class DepartmentTestBuilder {
    public static Department build() {
        return Department.builder()
                .departmentId(1)
                .departmentName("Development")
                .build();
    }
}
