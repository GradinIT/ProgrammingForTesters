package se.jensen.exercise.department;

import org.junit.Test;
import se.jensen.entity.Department;



public class TestThatNullPointerExceptionIsThrown {

    @Test(expected = NullPointerException.class)
    public void TestDepartmentIdINull() {
        Department.builder()
                .departmentId(null)
                .departmentName("Department 1")
                .build();
    }
    @Test(expected = NullPointerException.class)
    public void TestDepartmentNameNull() {
        Department.builder()
                .departmentId(1)
                .departmentName(null)
                .build();


    }

}


