package se.jocke.department.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.api.DepartmentModel;

public class TestCreateDepartmentModel {
    private final String DEPARTMENT_NAME = "Development";
    private final Integer DEPARTMENT_ID = Integer.valueOf(100);

    @Test
    public void testCreateDepartmentModel() {
        DepartmentModel department = DepartmentModel.builder()
                .departmentId(DEPARTMENT_ID)
                .departmentName(DEPARTMENT_NAME)
                .build();

        Assertions.assertEquals(DEPARTMENT_NAME, department.getDepartmentName());
        Assertions.assertEquals(DEPARTMENT_ID, department.getDepartmentId());
    }

    @Test
    public void testCreateDepartmentModelThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            DepartmentModel.builder().departmentName("Name").build();
        });
    }
}
