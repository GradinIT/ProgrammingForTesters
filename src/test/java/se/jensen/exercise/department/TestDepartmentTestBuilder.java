package se.jensen.exercise.department;

import se.jensen.entity.Department;
import se.jensen.exercise.test.builder.DepartmentTestBuilder;
import se.jensen.test.category.UnitTest;

import org.junit.experimental.categories.Category;
import org.junit.Assert;
import org.junit.Test;

@Category(UnitTest.class)

    public class TestDepartmentTestBuilder {
        @Test
        public void TestDepartmentTestBuilder()
        {
            DepartmentTestBuilder testBuilder = new DepartmentTestBuilder();
            Department result = testBuilder.build();
            Assert.assertNotNull(result);
            Assert.assertEquals(Integer.valueOf(1), result.getDepartmentId());
            Assert.assertEquals("Development", result.getDepartmentName());
        }
    }




