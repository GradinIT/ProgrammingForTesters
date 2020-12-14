package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.api.DepartmentModel;
import se.jensen.api.mapper.DepartmentModelMapper;
import se.jensen.entity.Department;
import se.jensen.exercise.test.builder.DepartmentTestBuilder;

public class TestDepartmentModelMapper {
    @Test
    public void testMapFromDepartmentToDepartmentModel(){
        Department expected = DepartmentTestBuilder.build();
        DepartmentModel departmentModel = DepartmentModelMapper.map(expected);
        Assert.assertNotNull(departmentModel);
        Assert.assertEquals(expected.getDepartmentId(),departmentModel.getDepartmentId());
        Assert.assertEquals(expected.getDepartmentName(),departmentModel.getDepartmentName());
    }
    @Test
    public void testMapFromDepartmentModelToDepartment(){
        DepartmentModel expected = DepartmentModelTestBuilder.build();
        Department department = DepartmentModelMapper.map(expected);
        Assert.assertNotNull(department);
        Assert.assertEquals(expected.getDepartmentId(), department.getDepartmentId());
        Assert.assertEquals(expected.getDepartmentName(), department.getDepartmentName());
    }
}
