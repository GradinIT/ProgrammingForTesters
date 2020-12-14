package se.jensen.exercise.department;

import se.jensen.api.DepartmentModel;
import se.jensen.api.mapper.DepartmentModelMapper;
import se.jensen.entity.Department;
import se.jensen.test.category.UnitTest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;


@Category(UnitTest.class)

public class TestDepartmentModelMapper {
    @Test
    public void testDepartmentModelMapperMethod1(){
        DepartmentModel departmentModel= DepartmentModel.builder()
                .departmentId(1)
                .departmentName("Development").build();
        Department result= DepartmentModelMapper.map(departmentModel);
        Assert.assertNotNull(result);
        Assert.assertEquals(Integer.valueOf(1),result.getDepartmentId());
        Assert.assertEquals("Development",result.getDepartmentName());
    }

    @Test
    public void testDepartmentModelMapperMethod2(){
        Department department= Department.builder().departmentId(2).departmentName("Sales").build();
        DepartmentModel result= DepartmentModelMapper.map(department);
        Assert.assertNotNull(result);
        Assert.assertEquals(Integer.valueOf(2),result.getDepartmentId());
        Assert.assertEquals("Sales",result.getDepartmentName());

    }
}


