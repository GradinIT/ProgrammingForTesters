package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.entity.Department;

import static org.assertj.core.api.Fail.fail;

public class TestThatNullPointerExceptionIsThrown {
    @Test(expected = NullPointerException.class)
    public void testDepartmentIdIsNull(){
        Department department= Department.builder().departmentId(null)
                .departmentName("Finance")
                .build();
        fail("exception not thrown");
    }
    @Test(expected = NullPointerException.class)
    public void testDepartmentNameIsNull(){
        Department department= Department.builder().departmentId(1)
                .departmentName(null)
                .build();
        fail("exception not thrown");
    }
}
