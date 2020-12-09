package se.jensen.exercise.test.builder;

import se.jensen.api.DepartmentModel;

public class DepartmentModelTestBuilder {
    public static DepartmentModel build() {
        return DepartmentModel.builder()
                .departmentId(1)
                .departmentName("Junit")
                .build();
    }
}
