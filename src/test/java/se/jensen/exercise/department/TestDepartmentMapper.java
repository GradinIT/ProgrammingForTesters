package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.api.DepartmentModel;
import se.jensen.api.mapper.DepartmentModelMapper;
import se.jensen.entity.Department;


public class TestDepartmentMapper {
    @Test
    public void TestDepartmentMap (){
        String departmentName = "metal";
        Integer departmentId = 2;


        DepartmentModel departmentModel = DepartmentModel.builder()
                .departmentId(departmentId)
                .departmentName(departmentName)
                .build();

        Department department = DepartmentModelMapper.map(departmentModel);
        Assert.assertNotNull(department);
        Assert.assertEquals(departmentModel.getDepartmentId(),department.getDepartmentId());
        Assert.assertEquals(departmentModel.getDepartmentName(),department.getDepartmentName());

    }
    @Test
    public void TestDepartmentmodelMap (){

        String departmentName = "metal";
        Integer departmentId = 2;

        Department department = Department.builder()
                .departmentId(departmentId)
                .departmentName(departmentName)
                .build();

        DepartmentModel departmentModel = DepartmentModelMapper.map(department);
        Assert.assertNotNull(departmentModel);
        Assert.assertEquals(departmentModel.getDepartmentId(),department.getDepartmentId());
        Assert.assertEquals(departmentModel.getDepartmentName(),department.getDepartmentName());

    }
}
