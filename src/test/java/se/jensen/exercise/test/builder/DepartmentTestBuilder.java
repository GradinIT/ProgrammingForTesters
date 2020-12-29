package se.jensen.exercise.test.builder;

import se.jensen.entity.Department;

public class DepartmentTestBuilder {
    private static Department.DepartmentBuilder builder = Department.builder();

    public static Department build() {
        return Department.builder()
                .departmentId(1)
                .departmentName("Development")
                .build();
    }

    public static Department.DepartmentBuilder builder() {
        return builder;
    }
}
