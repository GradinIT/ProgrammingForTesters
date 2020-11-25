package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.entity.Department;

public class TestThatDepartmentIsCreated {

    @Test
    public void test() {
        String departmentName = "departmentName";
        Integer departmentId = 2;

        Department department = Department.builder()
                .departmentName(departmentName)
                .departmentId(departmentId)
                .build();

        Assert.assertEquals(departmentId,department.getDepartmentId());
        Assert.assertEquals(departmentName,department.getDepartmentName());
    }
}