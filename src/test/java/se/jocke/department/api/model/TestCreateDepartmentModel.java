package se.jocke.department.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.api.DepartmentModel;
import se.jocke.department.builder.DepartmentModelTestBuilder;

public class TestCreateDepartmentModel {
    private final DepartmentModel DEPARTMENT_MODEL = DepartmentModelTestBuilder.builder().build();


    @Test
    public void testCreateDepartmentModel() {
        DepartmentModel department = DepartmentModel.builder()
                .departmentId(DEPARTMENT_MODEL.getDepartmentId())
                .departmentName(DEPARTMENT_MODEL.getDepartmentName())
                .build();

        Assertions.assertEquals(DEPARTMENT_MODEL.getDepartmentName(), department.getDepartmentName());
        Assertions.assertEquals(DEPARTMENT_MODEL.getDepartmentId(), department.getDepartmentId());
        Assertions.assertEquals(DEPARTMENT_MODEL,department);
    }

    @Test
    public void testCreateDepartmentModelThrowsException() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            DepartmentModel.builder().departmentName("Name").build();
        });
    }
}
