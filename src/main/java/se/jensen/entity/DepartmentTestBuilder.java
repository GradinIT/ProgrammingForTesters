package se.jensen.entity;public class DepartmentTestBuilder {
    public Department build() {
        return Department.builder()
                .departmentId(1)
                .departmentName("Development")
                .build();
    }
}
