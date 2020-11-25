package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.entity.Department;

import static org.junit.Assert.fail;

public class TestThatNullPointerExceptionIsThrown {

    @Test(expected = NullPointerException.class)
    public void testDepartmentIdIsNull(){
         Department department = Department.builder()
                .departmentId(null)
                .departmentName("Finance")
                .build();
        fail("Exception was not thrown as expected ");
    }

    @Test(expected = NullPointerException.class)
    public void testDepartmentNameIsNull(){
       Department department =  Department.builder()
                .departmentId(1)
                .departmentName(null)
                .build();
       fail("Exception was not thrown as expected ");
    }

}