package se.jensen.exercise.department;


import org.junit.Test;
import se.jensen.entity.Department;

public class TestThatNullPointerExceptionIsThrown {

    @Test(expected = NullPointerException.class)
    public void testDepartmentIdIsNull(){
         Department.builder()
                .departmentId(null)
                .departmentName("Finance")
                .build();
        //Assert.assertEquals(null,department.getDepartmentId());
    }

    @Test(expected = NullPointerException.class)
    public void testDepartmentNameIsNull(){
        Department.builder()
                .departmentId(1)
                .departmentName(null)
                .build();
       // Assert.assertEquals(null,department.getDepartmentName());
    }
}