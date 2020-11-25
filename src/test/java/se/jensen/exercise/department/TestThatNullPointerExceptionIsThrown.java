package se.jensen.exercise.department;

import org.junit.Test;
import se.jensen.entity.Department;

public class TestThatNullPointerExceptionIsThrown {
    @Test(expected = NullPointerException.class)
    public void testDepartmentIsIdNull(){
        Department.builder().departmentId(null)
                .departmentName("Finance")
                .build();

    }
    @Test(expected = NullPointerException.class)
    public void testDepartmentNameIsIdNull(){
        Department.builder().departmentId(1)
                .departmentName(null)
                .build();

    }
}
