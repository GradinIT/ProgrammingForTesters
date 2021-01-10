package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import se.jensen.entity.Department;
import se.jensen.test.category.UnitTest;

@Category(UnitTest.class)

public class TestThatDepartmentIsCreated {

    @Test
    public void testToCreate() {
        Integer departmentId = 1;
        String departmentName = "salesDepartment";

        Department department = Department.builder()
                .departmentId(departmentId)
                .departmentName(departmentName)
                .build();

        Assert.assertEquals(departmentId, department.getDepartmentId());
        Assert.assertEquals(departmentName, department.getDepartmentName());
    }
}