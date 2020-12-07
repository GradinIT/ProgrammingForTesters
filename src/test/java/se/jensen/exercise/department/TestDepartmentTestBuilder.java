package se.jensen.exercise.department;
import org.junit.Assert;
import org.junit.Test;
import se.jensen.entity.Department;
import se.jensen.exercise.DepartmentTestBuilder;
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




