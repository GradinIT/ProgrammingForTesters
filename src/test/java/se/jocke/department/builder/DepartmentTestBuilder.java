package se.jocke.department.builder;

import se.jocke.entity.Department;

public class DepartmentTestBuilder {
    public static Department.DepartmentBuilder builder() {
        return Department.builder()
                .departmentId(100)
                .departmentName("Test/QA");
    }
}
