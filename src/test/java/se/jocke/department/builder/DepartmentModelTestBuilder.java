package se.jocke.department.builder;

import se.jocke.department.api.DepartmentModel;

public class DepartmentModelTestBuilder {
    public static DepartmentModel.DepartmentModelBuilder builder() {
        return DepartmentModel.builder()
                .departmentName("Develop")
                .departmentId(800);
    }
}
