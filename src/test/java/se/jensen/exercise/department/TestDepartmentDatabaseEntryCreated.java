package se.jensen.exercise.department;

import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.test.category.UnitTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;


@Category(UnitTest.class)

public class TestDepartmentDatabaseEntryCreated {
    @Test
    public void testDepartmentDatabaseEntryCreated()
    {
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(1)
                .departmentName("NewDevelopment")
                .build();
        Assert.assertNotNull(departmentDatabaseEntry);
        Assert.assertEquals(Integer.valueOf(1), departmentDatabaseEntry.getDepartmentId());
        Assert.assertEquals("NewDevelopment", departmentDatabaseEntry.getDepartmentName());
    }
}