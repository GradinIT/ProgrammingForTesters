package se.jocke.department.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.builder.DepartmentTestBuilder;

public class TestCreateDepartment {
    private final String DEPARTMENT_NAME = "Development";
    private final Integer DEPARTMENT_ID = Integer.valueOf(100);

    @Test
    public void testCreateDepartment() {
        Department department = DepartmentTestBuilder.builder()
                .departmentId(DEPARTMENT_ID)
                .departmentName(DEPARTMENT_NAME)
                .build();

        Assertions.assertEquals(DEPARTMENT_NAME, department.getDepartmentName());
        Assertions.assertEquals(DEPARTMENT_ID, department.getDepartmentId());
    }

    @Test
    public void testCreateDepartmentThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            Department.builder().departmentName("Name").build();
        });
    }
}
