package se.jensen.exercise.department;

import se.jensen.api.DepartmentModel;
import se.jensen.entity.Department;
import se.jensen.test.category.UnitTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;


@Category(UnitTest.class)

public class TestDepartmentModelCreated {
    @Test
    public void testDepartmentModelCreated(){
        final Integer departmentId = 1;
        final String departmentName= "newDepartment";

        DepartmentModel departmentModel = DepartmentModel.builder()
                .departmentId(departmentId)
                .departmentName(departmentName)
                .build();

        Assert.assertNotNull(departmentModel);
        Assert.assertEquals(departmentId, departmentModel.getDepartmentId());
        Assert.assertEquals(departmentName, departmentModel.getDepartmentName());
    }

    @Test (expected = NullPointerException.class)
    public void testNonNullExceptionDepartmentId ()
    {
        DepartmentModel.builder()
            .departmentId(null)
            .departmentName("Department")
            .build();
    }

    @Test (expected = NullPointerException.class)
    public void testNonNullExceptionDepartmentName ()
    {
        DepartmentModel.builder()
                .departmentId(1)
                .departmentName(null)
                .build();
    }

}
