/*package se.jensen.exercise.department;

import org.junit.Assert;
import org.junit.Test;
import se.jensen.api.DepartmentModel;

import se.jensen.api.mapper.DepartmentModelMapper;
import se.jensen.api.mapper.DepartmentModelsMapper;
import se.jensen.entity.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestDepartmentModelsmapper {
    @Test
    public void TestDepartmentModelsMap (){
        String departmentName = "metal";
        Integer departmentId = 2;
        String departmentName2 = "wood";
        Integer departmentId2 = 3;


        Department department = Department.builder()
                .departmentId(departmentId)
                .departmentName(departmentName)
                .build();
        Department department2 = Department.builder()
                .departmentId(departmentId2)
                .departmentName(departmentName2)
                .build();
        List<Department> list = new ArrayList<>();
        list.add(department);
        list.add(department2);

        List<DepartmentModel> departmentModelList = DepartmentModelsMapper.map(list);



        Assert.assertNotNull(departmentModelList);
        Assert.assertNotNull(list);

        Assert.assertEquals(list,departmentModelList.stream().map(DepartmentModelMapper::map).collect(Collectors.toList()));



    }
}*/
