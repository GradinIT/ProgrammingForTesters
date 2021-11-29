package se.jocke.department.unittest.api.mapper;

import se.jocke.department.unittest.api.EmployeeModel;
import se.jocke.department.unittest.entity.Department;

public class DepartmentModelMapper {
    public static Department map (EmployeeModel model) {
        return Department.builder()
                .departmentName(model.getDepartmentName())
                .departmentId(model.getDepartmentId())
                .build();
    }
    public static EmployeeModel map (Department department) {
        return EmployeeModel.builder()
                .departmentId(department.getDepartmentId())
                .departmentName(department.getDepartmentName())
                .build();
    }
}
