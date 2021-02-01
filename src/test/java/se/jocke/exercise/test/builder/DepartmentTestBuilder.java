package se.jocke.exercise.test.builder;

import se.jocke.entity.Department;

public class DepartmentTestBuilder {
    public Department build() {
        return Department.builder()
                .departmentId(1)
                .departmentName("Development")
                .build();
    }
}
