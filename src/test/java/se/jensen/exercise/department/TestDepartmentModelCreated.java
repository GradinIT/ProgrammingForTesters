package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.api.DepartmentModel;
import se.jensen.entity.Department;

public class TestDepartmentModelCreated {
    @Test
    public void test() {
        Integer departmentId = 1;
        String departmentName = "Sales";
        DepartmentModel departmentModel = DepartmentModel.builder()
                .departmentId(departmentId)
                .departmentName(departmentName)
                .build();
        Assert.assertEquals(departmentId, departmentModel.getDepartmentId());
        Assert.assertEquals(departmentName, departmentModel.getDepartmentName());
    }
}
