package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.api.DepartmentModel;
import se.jensen.api.mapper.DepartmentModelsMapper;
import se.jensen.entity.Department;

import java.util.ArrayList;
import java.util.List;

public class TestDepartmentModelsMapper {
    @Test
            public void testDepartmentModelsMapper(){

        List<Department> departments = new ArrayList<>();
    departments.add(Department.builder()
            .departmentId(4)
            .departmentName("Fritids")
            .build());
        departments.add(Department.builder()
                .departmentId(5)
                .departmentName("Child care")
                .build());
        List<DepartmentModel> departmentModels = DepartmentModelsMapper.map(departments);
        Assert.assertNotNull(departmentModels);
        Assert.assertEquals(2,departmentModels.size());
        Assert.assertEquals(Integer.valueOf(4),departmentModels.get(0).getDepartmentId());
    }




    }
