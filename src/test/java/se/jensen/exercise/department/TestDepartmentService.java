package se.jensen.exercise.department;

import se.jensen.entity.Department;

public class TestDepartmentService {
    public void setUp() {
        Department[] arrayOfDepartments = new Department[] {Department.builder().build(), Department.builder().build()};
    }
}
