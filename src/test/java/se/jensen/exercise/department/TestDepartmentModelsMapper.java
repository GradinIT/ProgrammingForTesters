package se.jensen.exercise.department;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import se.jensen.api.DepartmentModel;
import se.jensen.api.mapper.DepartmentModelsMapper;
import se.jensen.entity.Department;

import java.util.ArrayList;
import java.util.List;

public class TestDepartmentModelsMapper {
    @Test
    public void testDepartmentModelsMapper(){
        List<Department> departmentList = new ArrayList<>();
        departmentList.add(Department.builder()
                .departmentId(1)
                .departmentName("Department1")
                .build());
        List<DepartmentModel> modelList = DepartmentModelsMapper.map(departmentList);
        Assert.assertEquals(1,modelList.size());
        Assert.assertEquals(Integer.valueOf(1),modelList.get(0).getDepartmentId());
        Assert.assertEquals("Department1",modelList.get(0).getDepartmentName());
    }
}
