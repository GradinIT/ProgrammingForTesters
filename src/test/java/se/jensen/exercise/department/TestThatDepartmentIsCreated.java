package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.entity.Department;
import se.jensen.entity.Employee;

public class TestThatDepartmentIsCreated {



    @Test
    public void TestDepartmentValues_IdAndName() {
        Integer departmentId = 1;
        String departmentName = "Department 1";

        Department department = Department.builder()
                .departmentId(departmentId)
                .departmentName(departmentName)
                .build();

        Assert.assertEquals(departmentId, department.getDepartmentId());
        Assert.assertEquals(departmentName, department.getDepartmentName());


    }
}
