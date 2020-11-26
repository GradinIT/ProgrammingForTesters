package se.jensen.exercise.department;

import org.junit.*;
import org.junit.experimental.categories.Category;
import se.jensen.entity.Department;

import se.jensen.test.category.UnitTest;

@Category(UnitTest.class)

public class TestThatNullPointerExceptionIsThrown {

    @Test (expected = NullPointerException.class)
    public void testThatDepartmentIdCanBeNull ()
    {
        System.out.println("Test that department id can't be null");

        Department department = Department.builder()
                .departmentId(null)
                .departmentName("Testers")
                .build();
        Assert.assertEquals(null, department.getDepartmentId());
    }

    @Test (expected = NullPointerException.class)
    public void testThatDepartmentNameCanBeNull ()
    {
        System.out.println("Test that department name can't be null");

        Department department = Department.builder()
                .departmentId(1)
                .departmentName(null)
                .build();
        Assert.assertEquals(null, department.getDepartmentName());
    }
}

