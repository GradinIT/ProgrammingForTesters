package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.api.DepartmentModel;
import se.jensen.test.category.UnitTest;

public class TestDepartmentModelCreated implements UnitTest {
    @Test
    public void testThatDepartmentModelCreated() {
        DepartmentModel departmentModel = DepartmentModel.builder()
                .departmentId(19)
                .departmentName("Class")
                .build();
        Assert.assertNotNull(departmentModel);
        Assert.assertEquals(Integer.valueOf(19),departmentModel.getDepartmentId());
    }
}
