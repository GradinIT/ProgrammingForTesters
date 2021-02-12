package se.jocke.builder;

import se.jocke.department.entity.Department;

public class DepartmentTestBuilder {
    public static Department.DepartmentBuilder builder() {
        return Department.builder()
                .departmentId(100)
                .departmentName("Test/QA");
    }
}
