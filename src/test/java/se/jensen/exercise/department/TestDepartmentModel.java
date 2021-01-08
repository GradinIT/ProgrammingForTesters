package se.jensen.exercise.department;
import org.junit.Test;
import org.junit.Assert;
import se.jensen.api.DepartmentModel;

public class TestDepartmentModel {

    @Test
    public void testBuilder(){
        String departmentName = "Helvete!";
        Integer departmentId = 1;
        DepartmentModel departmentModel = DepartmentModel.builder()
                .departmentId(1).departmentName(departmentName).build();
        Assert.assertNotNull(departmentModel);
        Assert.assertEquals(departmentId,departmentModel.getDepartmentId());
        Assert.assertEquals(departmentName,departmentModel.getDepartmentName());

    }

    @Test
    public void testToString(){
        String departmentName = "av";
        Integer departmentId = 1;

        String expected = DepartmentModel.builder()
                .departmentId(departmentId)
                .departmentName(departmentName)
                .build().toString();
        DepartmentModel departmentModel = DepartmentModel.builder()
                .departmentName(departmentName)
                .departmentId(departmentId)
                .build();

        Assert.assertEquals(expected, departmentModel.toString());
    }

}
