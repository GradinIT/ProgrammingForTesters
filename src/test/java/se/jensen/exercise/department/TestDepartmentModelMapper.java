package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.api.DepartmentModel;
import se.jensen.api.mapper.DepartmentModelMapper;
import se.jensen.entity.Department;
import se.jensen.exercise.test.builder.DepartmentModelTestBuilder;
import se.jensen.exercise.test.builder.DepartmentTestBuilder;

public class TestDepartmentModelMapper {
    @Test
    public void testMappingFromDepartmentToDepartmentModel() {
        Department departmentExpected = DepartmentTestBuilder.build();
        DepartmentModel departmentModel = DepartmentModelMapper.map(departmentExpected);
        Assert.assertNotNull(departmentModel);
        Assert.assertEquals(departmentExpected.getDepartmentId(),departmentModel.getDepartmentId());
        Assert.assertEquals(departmentExpected.getDepartmentName(),departmentModel.getDepartmentName());
    }
    @Test
    public void testMappingFromDepartmentModelToDepartment() {
        DepartmentModel departmentModelExpected = DepartmentModelTestBuilder.build();
        Department department = DepartmentModelMapper.map(departmentModelExpected);
        Assert.assertNotNull(department);
        Assert.assertEquals(departmentModelExpected.getDepartmentId(),department.getDepartmentId());
        Assert.assertEquals(departmentModelExpected.getDepartmentName(),department.getDepartmentName());
    }
}
