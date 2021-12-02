package se.jocke.department.unittest.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import se.jocke.department.entity.Department;
import se.jocke.department.test.builder.DepartmentTestBuilder;

public class TestDepartment {
    private static final Department DEPARTMENT = DepartmentTestBuilder.build();

    @Test
    public void testThatDepartmentIsCreated () {
        Department department = Department.builder()
                .departmentId(DEPARTMENT.getDepartmentId())
                .departmentName(DEPARTMENT.getDepartmentName())
                .build();

        Assertions.assertEquals(DEPARTMENT,department);
        Assertions.assertEquals(DEPARTMENT.getDepartmentName(), department.getDepartmentName());
        Assertions.assertEquals(DEPARTMENT.getDepartmentId(), department.getDepartmentId());
        String DEPARTMENT_TO_STRING_VALUE = DEPARTMENT.toString();
        String department_to_string_value = department.toString();
        Assertions.assertEquals(DEPARTMENT_TO_STRING_VALUE,department_to_string_value);


    }
    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingDepartmentId() {
        Assertions.assertThrows(NullPointerException.class ,
                () -> Department.builder().departmentName(DEPARTMENT.getDepartmentName()).build());

    }
    @Test
    public void testThatNullPointerExceptionIsRaisedWhenNotProvidingDepartmentName() {
        Assertions.assertThrows(NullPointerException.class ,
                () -> Department.builder().departmentId(DEPARTMENT.getDepartmentId()).build());

    }
}
