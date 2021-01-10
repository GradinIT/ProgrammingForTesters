package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.api.DepartmentModel;
import se.jensen.api.mapper.DepartmentModelMapper;
import se.jensen.api.mapper.DepartmentModelsMapper;
import se.jensen.entity.Department;
import se.jensen.exercise.test.builder.DepartmentModelTestBuilder;
import se.jensen.exercise.test.builder.DepartmentTestBuilder;
import se.jensen.test.category.UnitTest;

import java.util.ArrayList;
import java.util.List;

public class TestDepartmentModelMapper implements UnitTest {

    @Test
    public void testMapFromDepartmentModelToDepartment() {
        DepartmentModel expected = DepartmentModelTestBuilder.build();
        Department department = DepartmentModelMapper.map(expected);
        Assert.assertNotNull(department);
        Assert.assertEquals(expected.getDepartmentId(),department.getDepartmentId());
        Assert.assertEquals(expected.getDepartmentName(),department.getDepartmentName());
    }
    @Test
    public void testMapFromDepartmentToDepartmentModel() {
        Department expected = DepartmentTestBuilder.build();
        DepartmentModel departmentModel = DepartmentModelMapper.map(expected);
        Assert.assertNotNull(departmentModel);
        Assert.assertEquals(expected.getDepartmentId(),departmentModel.getDepartmentId());
        Assert.assertEquals(expected.getDepartmentName(),departmentModel.getDepartmentName());
    }
    @Test
    public void testDepartmentModelsMapper(){
        List<Department> departmentsList = new ArrayList<>();
        departmentsList.add(Department.builder()
                .departmentId(7)
                .departmentName("School")
                .build());
        departmentsList.add(Department.builder()
                .departmentId(8)
                .departmentName("Kindergarden")
                .build());
        List<DepartmentModel> departmentModelList = DepartmentModelsMapper.map(departmentsList);
        Assert.assertNotNull(departmentModelList);
        Assert.assertEquals(2,departmentModelList.size());
        Assert.assertEquals("School",departmentModelList.get(0).getDepartmentName());
        Assert.assertEquals(Integer.valueOf(7), departmentModelList.get(0).getDepartmentId());
        Assert.assertEquals(Integer.valueOf(8),departmentModelList.get(1).getDepartmentId());
        Assert.assertEquals("Kindergarden",departmentModelList.get(1).getDepartmentName());
    }
}
