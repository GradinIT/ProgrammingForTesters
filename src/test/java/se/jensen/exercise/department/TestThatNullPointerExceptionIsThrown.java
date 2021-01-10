package se.jensen.exercise.department;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import se.jensen.entity.Department;
import se.jensen.test.category.UnitTest;

@Category(UnitTest.class)

public class TestThatNullPointerExceptionIsThrown {

    @Test(expected = NullPointerException.class)
    public void testThrowNullPointerExceptionId() {
        Department.builder()
                .departmentId(null)
                .departmentName("salesDepartment")
                .build();
    }

    @Test(expected = NullPointerException.class)
    public void testThrowNullPointerExceptionName() {
        Department.builder()
                .departmentId(1)
                .departmentName(null)
                .build();
    }
}

