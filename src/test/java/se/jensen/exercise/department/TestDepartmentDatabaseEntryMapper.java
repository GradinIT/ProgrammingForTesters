package se.jensen.exercise.department;

import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.entity.Department;
import se.jensen.dao.mapper.DepartmentDatabaseEntryMapper;
import se.jensen.test.category.UnitTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import java.util.ArrayList;
import java.util.List;

@Category(UnitTest.class)

public class TestDepartmentDatabaseEntryMapper {
    @Test
    public void testDepartmentDatabaseEntryMapperMethod1()
    {
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(2)
                .departmentName("Department2")
                .build();
        Department result = DepartmentDatabaseEntryMapper.map(departmentDatabaseEntry);
        Assert.assertNotNull(result);
        Assert.assertEquals(Integer.valueOf(2), result.getDepartmentId());
        Assert.assertEquals("Department2", result.getDepartmentName());
    }
    @Test
    public void testDepartmentDatabaseEntryMapperMethod2()
    {
        Department department = Department.builder()
                .departmentId(10)
                .departmentName("Department10")
                .build();
        DepartmentDatabaseEntry result = DepartmentDatabaseEntryMapper.map(department);
        Assert.assertNotNull(result);
        Assert.assertEquals(Integer.valueOf(10), result.getDepartmentId());
        Assert.assertEquals("Department10", result.getDepartmentName());
    }
    @Test
    public void testDepartmentDatabaseEntryMapperList()
    {
        List<DepartmentDatabaseEntry> list = new ArrayList<>();
        list.add (DepartmentDatabaseEntry.builder()
                .departmentId(11)
                .departmentName("Department11")
                .build());
        List <Department> result = DepartmentDatabaseEntryMapper.map(list);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(list.get(0).getDepartmentId(), result.get(0).getDepartmentId());
        Assert.assertEquals(list.get(0).getDepartmentName(), result.get(0).getDepartmentName());
    }
}
