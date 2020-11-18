package se.jensen.api.mapper;

import se.jensen.api.DepartmentModel;
import se.jensen.entity.Department;

public class DepartmentModelMapper {
    public static Department map (DepartmentModel model) {
        return Department.builder()
                .departmentName(model.getDepartmentName())
                .departmentId(model.getDepartmentId())
                .build();
    }
    public static DepartmentModel  map (Department department) {
        return DepartmentModel.builder()
                .departmentId(department.getDepartmentId())
                .departmentName(department.getDepartmentName())
                .build();
    }
}
