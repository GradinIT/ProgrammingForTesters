package se.jensen.api.mapper;

import se.jensen.api.DepartmentModel;
import se.jensen.entity.Department;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentModelsMapper {
    public static List<DepartmentModel> map(List<Department> departments) {
        return departments.stream().map(DepartmentModelMapper::map).collect(Collectors.toList());
    }
}
