package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.dao.DepartmentDatabaseEntry;

public class TestDepartmentDatabaseEntry {
    @Test
    public void testThatDepartmentDatabaseEntryCreated(){
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(20)
                .departmentName("Games")
                .build();
        Assert.assertNotNull(departmentDatabaseEntry);
        Assert.assertEquals(Integer.valueOf(20),departmentDatabaseEntry.getDepartmentId());
        Assert.assertEquals("Games",departmentDatabaseEntry.getDepartmentName());
    }
}
