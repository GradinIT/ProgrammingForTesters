package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.dao.mapper.DepartmentDatabaseEntryMapper;
import se.jensen.entity.Department;

import java.util.ArrayList;
import java.util.List;

public class TestDepartmentDataBaseEntry {
    @Test
    public void TestMapToDepartment(){
        DepartmentDatabaseEntry databaseEntry = DepartmentDatabaseEntry.builder()
                .departmentName("ex")
                .departmentId(5)
                .build();

        Department department = DepartmentDatabaseEntryMapper.map(databaseEntry);

        Assert.assertNotNull(department);
        Assert.assertEquals(databaseEntry.getDepartmentId(),department.getDepartmentId());
        Assert.assertEquals(department.getDepartmentName(),databaseEntry.getDepartmentName());
    }

    @Test
    public void TestMapToDatabaseEntry(){
        Department department = Department.builder()
                .departmentName("extra")
                .departmentId(6)
                .build();
        DepartmentDatabaseEntry databaseEntry = DepartmentDatabaseEntryMapper.map(department);

        Assert.assertNotNull(databaseEntry);
        Assert.assertEquals(databaseEntry.getDepartmentName(), department.getDepartmentName());
        Assert.assertEquals(databaseEntry.getDepartmentId(), department.getDepartmentId());
    }

    @Test
    public void TestMapListToDepartment(){
        DepartmentDatabaseEntry databaseEntry = DepartmentDatabaseEntry.builder()
                .departmentName("ex")
                .departmentId(5)
                .build();

        List<DepartmentDatabaseEntry> departmentDatabaseEntryList = new ArrayList<>();
        departmentDatabaseEntryList.add(databaseEntry);

        List<Department> departmentList = DepartmentDatabaseEntryMapper.map(departmentDatabaseEntryList);
        
        Assert.assertNotNull(departmentList);
        Assert.assertEquals(departmentList.size(),departmentDatabaseEntryList.size());

    }
}
