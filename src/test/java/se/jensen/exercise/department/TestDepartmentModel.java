package se.jensen.exercise.department;
import org.junit.Test;
import org.junit.Assert;
import se.jensen.api.DepartmentModel;

public class TestDepartmentModel {

    @Test
    public void testBuilder(){
        String departmentName = "IT";
        Integer departmentId = 1;

        DepartmentModel departmentModel = DepartmentModel.builder()
                .departmentId(departmentId)
                .departmentName(departmentName)
                .build();

        Assert.assertNotNull(departmentModel);
        Assert.assertEquals(departmentId,departmentModel.getDepartmentId());
        Assert.assertEquals(departmentName,departmentModel.getDepartmentName());

    }

    @Test
    public void testToString(){
        String departmentName = "ekonomi";
        Integer departmentId = 1;


        DepartmentModel  expected = DepartmentModel.builder()
                .departmentName(departmentName)
                .departmentId(departmentId)
                .build();


        String departmentModel  = DepartmentModel.builder()
                .departmentId(departmentId)
                .departmentName(departmentName)
                .build().toString();


       Assert.assertEquals(expected.toString(),departmentModel);
        System.out.println(departmentModel + " "+expected);
    }

}
