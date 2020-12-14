package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.dao.mapper.DepartmentDatabaseEntryMapper;
import se.jensen.entity.Department;
import se.jensen.exercise.test.builder.DepartmentTestBuilder;

import java.util.ArrayList;
import java.util.List;

public class TestDepartmentDatabaseEntryMapperCreated {


    @Test
    public void testMapFromDepartmentToDepartmentDatabaseEntry() {
        Department department = Department.builder()
                .departmentId(2)
                .departmentName("Sales")
                .build();
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntryMapper.map(department);
        Assert.assertNotNull(departmentDatabaseEntry);
        Assert.assertEquals(department.getDepartmentId(), departmentDatabaseEntry.getDepartmentId());
        Assert.assertEquals(department.getDepartmentName(), departmentDatabaseEntry.getDepartmentName());

    }
    @Test
    public void testMapFromDepartmentDatabaseEntryToDepartment() {
        DepartmentDatabaseEntry departmentDatabaseEntry = DepartmentDatabaseEntry.builder()
                .departmentId(1)
                .departmentName("Finance")
                .build();
        Department department = DepartmentDatabaseEntryMapper.map(departmentDatabaseEntry);
        Assert.assertNotNull(department);
        Assert.assertEquals(departmentDatabaseEntry.getDepartmentId(), department.getDepartmentId());
        Assert.assertEquals(departmentDatabaseEntry.getDepartmentName(), department.getDepartmentName());

    }
    @Test
    public void testDepartmentDatabaseEntryMapper(){
        List<DepartmentDatabaseEntry> list = new ArrayList<>();
        list.add(DepartmentDatabaseEntry.builder()
                .departmentId(4)
                .departmentName("Sales")
                .build());
        List<Department> departments =DepartmentDatabaseEntryMapper.map(list);
        Assert.assertNotNull(departments);
        Assert.assertEquals(Integer.valueOf(4),departments.get(0).getDepartmentId());
        Assert.assertEquals("Sales",departments.get(0).getDepartmentName());
    }
}
