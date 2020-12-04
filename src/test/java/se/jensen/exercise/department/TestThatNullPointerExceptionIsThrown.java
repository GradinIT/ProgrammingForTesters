package se.jensen.exercise.department;

import se.jensen.entity.Department;
import se.jensen.test.category.UnitTest;

import org.junit.*;
import org.junit.experimental.categories.Category;

@Category(UnitTest.class)

public class TestThatNullPointerExceptionIsThrown {

    @Test (expected = NullPointerException.class)
    public void testDepartmentIdNotNull(){
        Department.builder()
                .departmentId(null)
                .departmentName("Department1")
                .build();
    }

    @Test (expected = NullPointerException.class)
    public void testDepartmentNameNotNull(){
        Department.builder()
                .departmentId(1)
                .departmentName(null)
                .build();
    }
}
