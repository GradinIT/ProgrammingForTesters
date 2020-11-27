package se.jensen.exercise.department;

import org.junit.Test;
import se.jensen.entity.Department;

public class TestThatNullPointerExceptionIsThrown {
    @Test(expected = NullPointerException.class)
    public void testThrowNullPointerExceptionId() {
        Department.builder()
                .departmentId(null)
                .departmentName("SalesDepartment")
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

