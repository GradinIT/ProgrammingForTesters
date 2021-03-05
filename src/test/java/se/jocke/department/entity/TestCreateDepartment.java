package se.jocke.department.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.department.builder.DepartmentTestBuilder;

public class TestCreateDepartment {
    private final Department DEPARTMENT = DepartmentTestBuilder.builder().build();

    @Test
    public void testCreateDepartment() {
        Department department = Department.builder()
                .departmentId(DEPARTMENT.getDepartmentId())
                .departmentName(DEPARTMENT.getDepartmentName())
                .build();
        Assertions.assertEquals(DEPARTMENT, department);
        Assertions.assertEquals(DEPARTMENT.getDepartmentName(), department.getDepartmentName());
        Assertions.assertEquals(DEPARTMENT.getDepartmentId(), department.getDepartmentId());
    }

    @Test
    public void testCreateDepartmentThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            Department.builder().departmentName(DEPARTMENT.getDepartmentName()).build();
        });
    }
}
