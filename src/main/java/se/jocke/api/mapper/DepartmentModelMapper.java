package se.jocke.api.mapper;

import se.jocke.api.DepartmentModel;
import se.jocke.department.entity.Department;

public class DepartmentModelMapper {

    // statisk metod som returnerar ett Department gjord med builder
    public static Department map(DepartmentModel model) {
        return Department.builder()
                .departmentName(model.getDepartmentName())
                .departmentId(model.getDepartmentId())
                .build();
    }

    // statisk metod som returnerar en DepartmentMODEL gjord med builder
    public static DepartmentModel map(Department department) {
        return DepartmentModel.builder()
                .departmentId(department.getDepartmentId())
                .departmentName(department.getDepartmentName())
                .build();
    }
}
