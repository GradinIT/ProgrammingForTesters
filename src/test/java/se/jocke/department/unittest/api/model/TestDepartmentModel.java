package se.jocke.department.unittest.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.department.unittest.api.EmployeeModel;
import se.jocke.department.test.builder.DepartmentModelTestBuilder;

public class TestDepartmentModel {
    private static final EmployeeModel DEPARTMENT_MODEL = DepartmentModelTestBuilder.build();

    @Test
    public void testThatDepartmentIsCreated() {
        EmployeeModel departmentModel = EmployeeModel.builder()
                .departmentId(DEPARTMENT_MODEL.getDepartmentId())
                .departmentName(DEPARTMENT_MODEL.getDepartmentName())
                .build();

        Assertions.assertEquals(DEPARTMENT_MODEL, departmentModel);
        Assertions.assertEquals(DEPARTMENT_MODEL.getDepartmentName(), departmentModel.getDepartmentName());
        Assertions.assertEquals(DEPARTMENT_MODEL.getDepartmentId(), departmentModel.getDepartmentId());
        Assertions.assertEquals(DEPARTMENT_MODEL.toString(), departmentModel.toString());
    }

    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingDepartmentId() {
        Assertions.assertThrows(NullPointerException.class,
                () -> EmployeeModel.builder().departmentName(DEPARTMENT_MODEL.getDepartmentName()).build());

    }

    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingDepartmentName() {
        Assertions.assertThrows(NullPointerException.class,
                () -> EmployeeModel.builder().departmentId(DEPARTMENT_MODEL.getDepartmentId()).build());

    }
}
