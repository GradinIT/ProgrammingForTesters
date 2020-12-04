package se.jensen.exercise.department;

import org.junit.*;
import org.junit.experimental.categories.Category;
import se.jensen.entity.Department;

import se.jensen.test.category.UnitTest;

@Category(UnitTest.class)

public class TestThatDepartmentIsCreated {

    @Test
    public void testThatDepartmentIsCreated()
    {
        Integer DEPARTMENTID = 1;
        String DEPARTMENTNAME= "newDepartment";

        Department department = Department.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build();

        Assert.assertNotNull(department);
        Assert.assertEquals(DEPARTMENTID, department.getDepartmentId());
        Assert.assertEquals(DEPARTMENTNAME, department.getDepartmentName());
    }

}

