package se.jocke.department.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.department.api.DepartmentModel;
import se.jocke.department.builder.DepartmentModelTestBuilder;
import se.jocke.department.builder.DepartmentTestBuilder;
import se.jocke.department.entity.Department;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDepartmentModelMapper {
    private final DepartmentModel DEPARTMENT_MODEL = DepartmentModelTestBuilder.builder().build();
    private final Department DEPARTMENT = DepartmentTestBuilder.builder().build();

    @Test
    public void testDepartmentToDepartmentModelMapping() {
        DepartmentModel model = DepartmentModelMapper.map(DEPARTMENT);
        Assertions.assertAll(
                () -> assertEquals(DEPARTMENT.getDepartmentId(), model.getDepartmentId()),
                () -> assertEquals(DEPARTMENT.getDepartmentName(), model.getDepartmentName())
        );
    }

    @Test
    public void testDepartmentModelToDepartmentMapping() {
        Department department = DepartmentModelMapper.map(DEPARTMENT_MODEL);
        Assertions.assertAll(
                () -> assertEquals(DEPARTMENT_MODEL.getDepartmentId(), department.getDepartmentId()),
                () -> assertEquals(DEPARTMENT_MODEL.getDepartmentName(), department.getDepartmentName())
        );
    }
}
