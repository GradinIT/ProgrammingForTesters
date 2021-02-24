package se.jocke.department.builder;

import se.jocke.department.entity.Department;

public class DepartmentTestBuilder {
    public static Department.DepartmentBuilder builderMethod() {
        return Department.builder()
                .departmentId(100)
                .departmentName("Test/QA");
    }
}
