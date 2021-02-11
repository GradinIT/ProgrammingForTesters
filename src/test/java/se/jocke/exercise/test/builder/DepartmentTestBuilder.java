package se.jocke.exercise.test.builder;

import se.jocke.entity.Department;

public class DepartmentTestBuilder {
    public Department.DepartmentBuilder builder() {
        return Department.builder()
                .departmentId(100)
                .departmentName("Test/QA");
    }
}
