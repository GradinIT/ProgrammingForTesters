package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.entity.Department;

public class TestDepartmentDatabaseEntryCreated {
    @Test
    public void test() {
        Integer DEPARTMENTID = 1;
        String DEPARTMENTNAME = "Accounts";
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENTID)
                .departmentName(DEPARTMENTNAME)
                .build();
        Assert.assertEquals(DEPARTMENTID, departmentDatabaseEntry.getDepartmentId());
        Assert.assertEquals(DEPARTMENTNAME, departmentDatabaseEntry.getDepartmentName());
    }
}
