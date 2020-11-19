package se.jensen.service;

import se.jensen.entity.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getDepartments();

    Department getDepartmentById(Integer departmentId);

    Department create(Department department);

    Department update(Department department);

    Department remove(Department department);
}
