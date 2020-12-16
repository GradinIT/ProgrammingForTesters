package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.entity.Department;

public class TestDepartment {
    @Test
    public void testThatDepartmentIsCreatedCorrectly(){
        Integer department_Id = 1;
        String department_Name = "Section A";
        Department department = Department.builder()
                .departmentId(department_Id)
                .departmentName(department_Name)
                .build();
        Assert.assertNotNull(department);
        Assert.assertEquals(department_Id,department.getDepartmentId());
        Assert.assertEquals(department_Name,department.getDepartmentName());
    }
}
