package se.jensen.exercise.department;

import se.jensen.api.DepartmentModel;

public class DepartmentModelTestBuilder {
    public static DepartmentModel build() {
        return DepartmentModel.builder()
                .departmentId(1)
                .departmentName("Sales")
                .build();
    }
}
