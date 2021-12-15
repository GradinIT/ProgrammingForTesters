package se.jocke.department.builder;

import se.jocke.department.entity.Department;
import se.jocke.department.entity.DepartmentId;

public class DepartmentTestBuilder {
    public static Department.DepartmentBuilder builder() {
        return Department.builder()
                .departmentId(DepartmentId.builder().id(1).build())
                .departmentName("Test/QA");
    }
}
