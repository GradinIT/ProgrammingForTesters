package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import se.jensen.entity.Department;
import se.jensen.exercise.test.builder.DepartmentTestBuilder;
import se.jensen.test.category.UnitTest;

@Category(UnitTest.class)

public class TestThatToStringReturnsProper {

    @Test
    public void testToStringReturn() {
        String expectedToString = DepartmentTestBuilder.build().toString();
        Department department = DepartmentTestBuilder.build();
        Assert.assertEquals(expectedToString, department.toString());
    }
}
