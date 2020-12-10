package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.dao.DepartmentDatabaseEntry;
import se.jensen.dao.mapper.DepartmentDatabaseEntryMapper;
import se.jensen.entity.Department;
import se.jensen.exercise.test.builder.DepartmentModelTestBuilder;
import se.jensen.exercise.test.builder.DepartmentTestBuilder;

public class TestDepartmentDatabaseEntryMapper {
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

}
