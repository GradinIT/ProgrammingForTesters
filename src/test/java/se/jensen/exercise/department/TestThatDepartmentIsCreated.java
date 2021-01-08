package se.jensen.exercise.department;
import org.junit.Assert;
import org.junit.Test;
import se.jensen.entity.Department;



public class TestThatDepartmentIsCreated {
    @Test
    public void test() {
        Integer departmentIdvalue = 1;
        String departmentnameValue = "firstName";


        Department department = Department.builder()
                .departmentId(departmentIdvalue)
                .departmentName(departmentnameValue)
                .build();

        Assert.assertEquals(departmentIdvalue,department.getDepartmentId());
        Assert.assertEquals(departmentnameValue,department.getDepartmentName());

    }
}
