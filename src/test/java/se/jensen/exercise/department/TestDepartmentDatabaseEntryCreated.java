package se.jensen.exercise.department;


import org.junit.Assert;
import org.junit.Test;
import se.jensen.entity.Department;

public class TestDepartmentDatabaseEntryCreated {
    @Test
    public void testDepartmentDatabaseEntryCreated(){
        Integer departmentId = 1;
        String departmentName= "newDepartment";

        Department departmentDatabaseEntry = Department.builder()
                .departmentId(departmentId)
                .departmentName(departmentName)
                .build();

        Assert.assertNotNull(departmentDatabaseEntry);
        Assert.assertEquals(departmentId, departmentDatabaseEntry.getDepartmentId());
        Assert.assertEquals(departmentName, departmentDatabaseEntry.getDepartmentName());
    }
}
