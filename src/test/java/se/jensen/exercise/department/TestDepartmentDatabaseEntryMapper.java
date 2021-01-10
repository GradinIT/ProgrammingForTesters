package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.dao.mapper.DepartmentDatabaseEntryMapper;
import se.jensen.entity.Department;
import se.jensen.exercise.test.builder.DepartmentTestBuilder;
import se.jensen.test.category.UnitTest;

import java.util.ArrayList;
import java.util.List;

public class TestDepartmentDatabaseEntryMapper implements UnitTest {
    private final Integer DEPARTMENT_ID= Integer.valueOf(10);
    private final String DEPARTMENT_NAME= "Test";
    @Test
    public void testMapFromDepartmentDatabaseEntryToDepartment() {
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(DEPARTMENT_ID)
                .departmentName(DEPARTMENT_NAME)
                .build();
        Department department = DepartmentDatabaseEntryMapper.map(departmentDatabaseEntry);
        Assert.assertNotNull(department);
        Assert.assertEquals(departmentDatabaseEntry.getDepartmentId(),department.getDepartmentId());
        Assert.assertEquals(departmentDatabaseEntry.getDepartmentName(),department.getDepartmentName());
    }

    @Test
    public void testMapFromDepartmentToDepartmentDatabaseEntryMapper(){
        Department department = DepartmentTestBuilder.build();
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntryMapper.map(department);
        Assert.assertNotNull(departmentDatabaseEntry);
        Assert.assertEquals(department.getDepartmentId(),departmentDatabaseEntry.getDepartmentId());
        Assert.assertEquals(department.getDepartmentName(),departmentDatabaseEntry.getDepartmentName());

    }
    @Test
    public void testDepartmentDatabaseEntriesMapper(){
        List<DepartmentDatabaseEntry> departmentDatabaseEntryList = new ArrayList<>();
        departmentDatabaseEntryList.add(DepartmentDatabaseEntry.builder()
                .departmentId(9)
                .departmentName("Sunshine")
                .build());
        departmentDatabaseEntryList.add(DepartmentDatabaseEntry.builder()
                .departmentId(10)
                .departmentName("Snowfall")
                .build());
        List<Department> departmentList = DepartmentDatabaseEntryMapper.map(departmentDatabaseEntryList);
         Assert.assertNotNull(departmentList);
         Assert.assertEquals(2,departmentList.size());
         Assert.assertEquals(Integer.valueOf(9),departmentList.get(0).getDepartmentId());
         Assert.assertEquals("Sunshine",departmentList.get(0).getDepartmentName());
         Assert.assertEquals(Integer.valueOf(10),departmentList.get(1).getDepartmentId());
         Assert.assertEquals("Snowfall",departmentList.get(1).getDepartmentName());
    }

}
