package se.jensen.exercise.department;

import org.junit.Test;
import se.jensen.entity.Department;
import se.jensen.test.category.UnitTest;


public class TestThatNullPointerExceptionIsThrown implements UnitTest {
    @Test(expected = NullPointerException.class)
    public void testDepartmentId(){
        Department.builder()
                .departmentId(null)
                .departmentName("Junit")
                .build();
    }
    @Test(expected = NullPointerException.class)
    public void testDepartmentName(){
        Department.builder()
                .departmentId(2)
                .departmentName(null)
                .build();
    }

}
